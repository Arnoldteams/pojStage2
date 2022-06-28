package com.cskaoyan.handler;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.mapper.MarketLogMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * 增加操作日志
 *
 * @author xyg2597@163.com
 * @since 2022/06/27 15:43
 */

@Aspect
@Component
public class LogHandler {

    @Autowired
    MarketLogMapper marketLogMapper;
    @Autowired
    HttpServletRequest request;

    //    @Pointcut("execution(* com.cskaoyan.controller..*(..))")
    @Pointcut("@annotation(com.cskaoyan.handler.LogAnnotation)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MarketLog marketLog = new MarketLog();
        HttpSession session = request.getSession();

//        获得当前增强方法
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String action = "";
        String successResult = "";
        String unSuccessResult = "";

//        获得增强类
        Object target = joinPoint.getTarget();
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (name.equals(declaredMethod.getName())) {
                LogAnnotation annotation = declaredMethod.getAnnotation(LogAnnotation.class);
                action = annotation.value();
                successResult = annotation.successResult();
                unSuccessResult = annotation.unSuccessResult();
            }
        }

        marketLog.setAction(action);


//        获得操作管理员名称
        String loginName = (String) session.getAttribute("username");
        marketLog.setAdmin(loginName);

//        插入ip
        marketLog.setIp(request.getRemoteHost());

//        操作时间
        Date date = new Date();
        marketLog.setAddTime(date);
        marketLog.setUpdateTime(date);

        BaseRespVo proceed = (BaseRespVo) joinPoint.proceed(); // 执行委托类的方法


        if (proceed.getErrno() == 0) {
            marketLog.setStatus(true);
        } else {
            marketLog.setStatus(false);
        }
        marketLog.setType(1);
        if ("成功".equals(proceed.getErrmsg())) {
            marketLog.setResult(successResult);
        } else {
            marketLog.setResult(unSuccessResult);
        }

        marketLogMapper.insertSelective(marketLog);
        return proceed;
    }

}














