package com.cskaoyan.handler;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author stone
 * @date 2022/06/29 09:58
 */
public class ValidationUtils {
//    public static BaseRespVo getBaseRespVo(BindingResult bindingResult) {
//        FieldError fieldError = bindingResult.getFieldError();
//        String field = fieldError.getField();//请求参数名
//        Object rejectedValue = fieldError.getRejectedValue();//错误的值
//        String defaultMessage = fieldError.getDefaultMessage(); //错误的消息
//        String msg = "请求参数" + field + "没有通过校验：值为" + rejectedValue + ";" + defaultMessage;
//        return BaseRespVo.invalidParameter(msg);
//    }

    public static void valid(BindingResult bindingResult) throws AdminRequestBodyException {
        if (bindingResult.hasFieldErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String field = fieldError.getField();//请求参数名
            Object rejectedValue = fieldError.getRejectedValue();//错误的值
            String defaultMessage = fieldError.getDefaultMessage(); //错误的消息
            String msg = "请求参数" + field + "没有通过校验：值为" + rejectedValue + ";" + defaultMessage;
            throw new AdminRequestBodyException(msg);
        }
    }
}
