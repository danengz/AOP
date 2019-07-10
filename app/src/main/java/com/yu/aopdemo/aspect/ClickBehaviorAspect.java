package com.yu.aopdemo.aspect;

import android.util.Log;

import com.yu.aopdemo.annotation.ClickBehavior;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class ClickBehaviorAspect {

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.yu.aopdemo.annotation.ClickBehavior * *(..))")
    public void pointCut() {}


    // 2、对切入点如何处理
    @Around("pointCut()")
    public Object jointPotin(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取类名
        String className = signature.getDeclaringType().getSimpleName();

        //获取方法名
        String methodName = signature.getName();

        //获取注解值
        String behavior = signature.getMethod().getAnnotation(ClickBehavior.class).value();

        Log.i("haha", String.format( "行为储存>>>在%s中调用了%s方法（%s）",className, methodName, behavior));

        return joinPoint.proceed();
    }
}
