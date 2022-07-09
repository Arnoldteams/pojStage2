package com.cskaoyan.order.biz.handler;

import com.alibaba.fastjson.JSON;
import com.cskaoyan.mall.commons.constant.SysRetCodeConstants;
import com.cskaoyan.mall.commons.exception.BaseBusinessException;
import com.cskaoyan.mall.commons.exception.BizException;
import com.cskaoyan.order.biz.context.CreateOrderContext;
import com.cskaoyan.order.biz.context.TransHandlerContext;
import com.cskaoyan.order.dal.entitys.Stock;
import com.cskaoyan.order.dal.persistence.StockMapper;
import com.cskaoyan.order.dto.CartProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description: 扣减库存处理器
 **/
@Component
@Slf4j
public class SubStockHandler extends AbstractTransHandler {

    @Autowired
    StockMapper stockMapper;

    @Override
    public boolean isAsync() {
        return false;
    }

    /**
     * @description: 生成订单前校验库存状态，抛异常或库存状态修改成功
     * @parameter: [context]
     * @return: boolean
     * @author: 帅关
     * @createTime: 2022/7/9 8:24
     */
    @Override
    @Transactional
    public boolean handle(TransHandlerContext context) {
        CreateOrderContext orderContext = (CreateOrderContext)context;
        List<CartProductDto> cartProductDtoList = orderContext.getCartProductDtoList();
        // 传入的商品数据不能为空
        if(cartProductDtoList == null || cartProductDtoList.size() == 0){
            throw new BizException(SysRetCodeConstants.REQUEST_DATA_NOT_EXIST.getCode(),
                    SysRetCodeConstants.REQUEST_DATA_NOT_EXIST.getMessage());
        }
        // 获取传入的商品列表的限购及数量信息
        List<Long> productIds = cartProductDtoList.stream()
                .map(CartProductDto::getProductId)
                .collect(Collectors.toList());
        orderContext.setBuyProductIds(productIds);
        List<Stock> items = stockMapper.findStocksForUpdate(productIds);

        // 库存未初始化
        if(items == null || items.size() == 0){
            throw new BizException("库存未初始化！");
        }

        // 部分库存为初始化
        if(items.size() != productIds.size()){
            throw new BizException("部分库存未初始化！");
        }

        // 比较限购数量和库存
        checkStockNumAndLimNum(cartProductDtoList, items);

        // 更新库存及冻结数量
        for (CartProductDto cartProductDto : cartProductDtoList) {
            Long productNum = cartProductDto.getProductNum();
            Long productId = cartProductDto.getProductId();

            Stock stock = new Stock();
            stock.setItemId(productId);
            stock.setStockCount(-productNum);
            stock.setLockCount(productNum.intValue());
            stockMapper.updateStock(stock);
        }

        return true;
    }

    /**
     * @description: 判断库存，限购数量是否合法
     * @parameter: [cartProductDtoList, items]
     * @return: void
     * @author: 帅关
     * @createTime: 2022/7/9 8:26
     */
    private void checkStockNumAndLimNum(List<CartProductDto> cartProductDtoList, List<Stock> items) {
        for (Stock item : items) {
            Integer restrictCountNum = item.getRestrictCount();
            Long stockCount = item.getStockCount();

            for (CartProductDto cartProductDto : cartProductDtoList) {
                Long purchaseNum = cartProductDto.getProductNum();

                if(item.getItemId().equals(cartProductDto.getProductId())){

                    if(purchaseNum < restrictCountNum){
                        throw new BizException(cartProductDto.getProductName() + "超出限购数量！");
                    }
                    if(purchaseNum < stockCount){
                        throw new BizException(cartProductDto.getProductName() + "超出库存数量");
                    }
                }
            }
        }
    }
}