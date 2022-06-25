package com.cskaoyan.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/***
 * @author: 于艳帆
 * @createTime: 2022-06-25 10:04:12
 * @description: response 返回格式
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
