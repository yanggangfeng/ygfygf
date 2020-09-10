package com.example.annotationdemo.user.aspectj;

import com.example.annotationdemo.user.util.BeanUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component // 将该类的创建交给Spring
@Aspect
/**
 * 凡是使用该类的话就进行使用切面的编程进行赋值
 */
public class NeedSetFileValueAspectJ {

    @Autowired
    private BeanUtil beanUtil;
    @Around("@annotation(com.example.annotationdemo.user.annotation.NeedSetFiledValue)")
    // 进行环绕通知 在功能的前后进行增强
    public Object doNeedSetFileValue(ProceedingJoinPoint joinPoint){
        // 进行预处理
        Object proceed = null;
        // 将处理的结果进行返回
        try {
              proceed = joinPoint.proceed();
            if (proceed instanceof List){
//                Class<?> classList = (Class<?>) proceed;
                beanUtil.setNeedFileValueForCollection((Collection) proceed);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

}


