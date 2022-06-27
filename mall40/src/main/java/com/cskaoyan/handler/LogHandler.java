//package com.cskaoyan.handler;
//
//import com.cskaoyan.bean.BaseRespVo;
//import com.cskaoyan.bean.MarketLog;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * 增加操作日志
// * @author xyg2597@163.com
// * @since 2022/06/27 15:43
// */
//
//@Aspect
//@Component
//public class LogHandler {
//
//
//
//    @Pointcut("execution(* com.cskaoyan.controller..*(..))")
//    public void servicePointcut() {}
//
//    @Around("servicePointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        MarketLog marketLog = new MarketLog();
//
//        BaseRespVo proceed = (BaseRespVo) joinPoint.proceed(); // 执行委托类的方法
//
//
//        if (proceed.getErrno() == 0) {
//            marketLog.setStatus(true);
//        }
//        else{
//            marketLog.setStatus(false);
//        }
//        return proceed;
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
