package com.example.annotationdemo.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// YGF
@Target({ElementType.FIELD}) // 作用于对象的属性上
@Retention(RetentionPolicy.RUNTIME) //JVM 运行时生效
public @interface NeedSetValue {

    Class<?> beanClazz(); // 需要调用的类
    String method(); // 需要调用的方法 单个参数
//    String [] methodArray(); // 需要调用的方法 多个参数
    String params(); // 需要传过去的参数
    String targetFiled(); // 获取需要的值
}
