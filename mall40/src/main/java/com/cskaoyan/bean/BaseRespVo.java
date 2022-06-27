package com.cskaoyan.bean;

import lombok.Data;

/**
 * @author stone
 * @date 2022/01/06 16:24
 */
@Data
public class BaseRespVo<T> {
    T data;
    String errmsg = "成功";
    int errno;

    public static <T> BaseRespVo ok(T data) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(data);
        return baseRespVo;
    }

    public static <T> BaseRespVo invalidData(String msg) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(504);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> BaseRespVo invalidData() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(504);
        baseRespVo.setErrmsg("更新数据已失效");
        return baseRespVo;
    }
    public static <T> BaseRespVo invalidParameter(String msg) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(400);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> BaseRespVo unAuthc() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(502);
        baseRespVo.setErrmsg("认证失败");
        return baseRespVo;
    }


    public static <T> BaseRespVo errParam() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(401);
        baseRespVo.setErrmsg("参数不对");
        return baseRespVo;
    }

    public static <T> BaseRespVo unableDelete() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(623);
        baseRespVo.setErrmsg("订单不能删除");
        return baseRespVo;
    }
}
