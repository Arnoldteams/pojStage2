package com.cskaoyan.handler;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.controller.AdminOrderController;
import com.cskaoyan.controller.AuthController;
import com.cskaoyan.mapper.MarketLogMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionManager;
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
public class LogAspecHandler {

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
        BaseRespVo proceed = (BaseRespVo) joinPoint.proceed(); // 执行委托类的方法

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
        Class<?> targetClass = target.getClass();
//        获得增强类中的所有方法，并得到对应的注解和参数
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (name.equals(declaredMethod.getName())) {
                LogAnnotation annotation = declaredMethod.getAnnotation(LogAnnotation.class);
//                如果该方法没有注解，说明进行了方法重载，跳过该方法
                if (annotation == null) {
                    continue;
                }
                action = annotation.value();
                successResult = annotation.successResult();
                unSuccessResult = annotation.unSuccessResult();
//                设置备注
                marketLog.setComment(annotation.comment());
                String log = (String) session.getAttribute("log");

                if (log == null) {
                    log = "";
                }


                marketLog.setAction(action);


//                获得操作管理员名称
                String loginName = (String) session.getAttribute("username");

                if (StringUtils.isEmpty(loginName)) {
                    marketLog.setAdmin("匿名用户");
                } else {

                    marketLog.setAdmin(loginName);
                }

//        插入ip
                marketLog.setIp(request.getRemoteHost());

//        操作时间
                Date date = new Date();
                marketLog.setAddTime(date);
                marketLog.setUpdateTime(date);

//        操作结果和操作状态
                if (proceed.getErrno() == 0) {
                    marketLog.setStatus(true);
                    marketLog.setResult(log + successResult);
                } else {
                    marketLog.setStatus(false);
                    if (StringUtils.isEmpty(unSuccessResult)) {
                        marketLog.setResult(log + proceed.getErrmsg());
                    } else {
                        marketLog.setResult(log + unSuccessResult);
                    }
                }
                marketLog.setType(1);
                marketLogMapper.insertSelective(marketLog);
            }
        }


        return proceed;
    }

}














