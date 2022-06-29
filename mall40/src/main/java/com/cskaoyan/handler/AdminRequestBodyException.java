package com.cskaoyan.handler;

/**
 * 自定义异常，用于获取接收JSON数据时的非法输入
 * @author xyg2597@163.com
 * @since 2022/06/29 13:20
 */

public class AdminRequestBodyException extends Exception {
    public AdminRequestBodyException(String message) {
        super(message);
    }
}
