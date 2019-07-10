package com.yu.aopdemo.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.yu.aopdemo.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoginCheckAspect {

    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 表示可以处理LoginCheck这个类所有的方法
    @Pointcut("execution(@com.yu.aopdemo.annotation.LoginCheck * *(..))")
    public void pointcut() {}

    // 2、对切入点如何处理
    @Around("pointcut()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Context context = (Context) joinPoint.getThis();
        boolean isLogin = context.getSharedPreferences("sp", context.MODE_PRIVATE).getBoolean("login", false);
        if (isLogin){
            Log.i("haha", "LoginCheck>>>已登录");
            return joinPoint.proceed();
        }else {
            Log.i("haha", "LoginCheck>>>未登录");
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
            return null;
        }
    }

}
