package com.cskaoyan.handler;

import com.cskaoyan.bean.BaseRespVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  应用程序的统一异常处理，发生异常时，提示“功能尚未开放”
 * @since 2022/06/27 08:31
 * @author Gzy
 */

@RestControllerAdvice
public class PostExceptionHandler {
    @Value("${com.cskaoyan.logPath}")
    private String logPath;

    /**
     * @description: 异常统一处理，并打印日志
     * 日志文件路径在配置文件中读取
     * 开发期间可以注释掉
     * @parameter: [e] 发生的异常
     * @return: com.cskaoyan.bean.BaseRespVo
     * @author: 帅关
     * @createTime: 2022/6/27 13:47
     */
    // @ExceptionHandler(Exception.class)
    // @ResponseBody
    public BaseRespVo allExceptionHandle(Exception e) throws Exception {
        File logFile = new File(logPath);
        try(FileOutputStream out = new FileOutputStream(logFile, true);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter)
        ){
            // 设置异常发生时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd : hh-mm-ss");
            String dateStr = sdf.format(new Date());
            // 获取异常信息
            e.printStackTrace(printWriter);
            out.write((dateStr + "\r\n" + stringWriter.toString() + "\r\n\r\n").getBytes());
            // 返回提示信息
            return BaseRespVo.AuthNotEnough("功能尚未開放！");
        }catch (Exception exp){
            exp.printStackTrace();
            return BaseRespVo.AuthNotEnough("功能尚未開放！");
        }
    }

    /**
     * 专题编辑和创建时，如果最低价不是数字，会发生该异常，直接返回提示即可，不用写入日志
     * @param e 错误信息
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/27 14:16
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @LogAnnotation(value = "编辑专题信息")
    public BaseRespVo JsonExceptionHandle(HttpMessageNotReadableException e){
        e.printStackTrace();
        return BaseRespVo.invalidJson("参数值错误");
    }

    @ExceptionHandler(AdminRequestBodyException.class)
    @ResponseBody
    public BaseRespVo JsonExceptionHandle(AdminRequestBodyException e){
        return BaseRespVo.invalidJson(e.getMessage());
    }
    


}
