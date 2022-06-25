package com.cskaoyan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月21日 21:39:53
 * @version:
 * @Description:
 */
@Data
public class Result<T>{
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    T data;
    String message;
    int errno;

    public static Result ok() {
        Result Result = new Result();
        Result.setMessage("成功");
        Result.setErrno(200);
        return Result;
    }
    public static <T> Result ok(T data) {
        Result Result = new Result();
        Result.setData(data);
        Result.setMessage("成功");
        Result.setErrno(200);
        return Result;
    }
}
