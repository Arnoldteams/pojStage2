package com.cskaoyan.shopping.dto;

import com.cskaoyan.mall.commons.exception.ValidateException;
import com.cskaoyan.mall.commons.result.AbstractRequest;
import com.cskaoyan.mall.constant.ShoppingRetCode;
import lombok.Data;


@Data
public class AddCartRequest extends AbstractRequest {

    private Long userId;
    private Long itemId; // 与json不一致
    private Integer num; // 与json不一致

    private Long productId;
    private Long productNum;

    @Override
    public void requestCheck() {
        if (userId == null || userId < 0) {
            // 参数非法
            throw new ValidateException(ShoppingRetCode.PARAM_VALIDATE_FAILD.getCode(),
                    ShoppingRetCode.PARAM_VALIDATE_FAILD.getMessage());
        }
        if (productId == null || productId < 0) {
            // 参数非法
            throw new ValidateException(ShoppingRetCode.PARAM_VALIDATE_FAILD.getCode(),
                    ShoppingRetCode.PARAM_VALIDATE_FAILD.getMessage());
        }
        if (productNum == null || productNum < 0) {
            // 参数非法
            throw new ValidateException(ShoppingRetCode.PARAM_VALIDATE_FAILD.getCode(),
                    ShoppingRetCode.PARAM_VALIDATE_FAILD.getMessage());
        }
    }
}
