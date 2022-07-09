package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.commons.exception.ExceptionProcessorUtils;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.converter.ContentConverter;
import com.cskaoyan.shopping.dal.entitys.Panel;
import com.cskaoyan.shopping.dal.entitys.PanelContentItem;
import com.cskaoyan.shopping.dal.persistence.PanelContentMapper;
import com.cskaoyan.shopping.dal.persistence.PanelMapper;
import com.cskaoyan.shopping.dto.HomePageResponse;
import com.cskaoyan.shopping.dto.PanelContentItemDto;
import com.cskaoyan.shopping.dto.PanelDto;
import com.cskaoyan.shopping.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author sprinkle
 * @since 2022/07/09 14:16
 */
@Service
public class IHomeServiceImpl implements IHomeService {

    @Autowired
    PanelMapper panelMapper;

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
    public HomePageResponse homepage() {
        HomePageResponse response = new HomePageResponse();


        try {
            List<Panel> panels = panelMapper.selectAll();
            Set<PanelDto> panelDtos = contentConverter.panels2Dto(panels);

            for (PanelDto panelDto : panelDtos) {
                List<PanelContentItem> panelContentItems = panelContentMapper.selectPanelContentAndProductWithPanelId(panelDto.getId());
                List<PanelContentItemDto> panelContentItemDtos = contentConverter.panelContentItem2Dto(panelContentItems);
                panelDto.setPanelContentItems(panelContentItemDtos);
            }

            //执行成功
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setPanelContentItemDtos(panelDtos);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }

        return response;
    }
}
