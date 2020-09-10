package com.example.annotationdemo.user.util;

import com.example.annotationdemo.user.annotation.NeedSetValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BeanUtil implements ApplicationContextAware {
    ApplicationContext applicationContext;

    public void setNeedFileValueForCollection(Collection col) throws Exception {
        // 处理相关结果集 List<OrderModel> userID userName
        // 1 进行对结果集的遍历处理
         Class<?> clazz = col.iterator().next().getClass();// class OderModel
        // 获取该类的所有的字段
        Field[] fields = clazz.getDeclaredFields(); // userID, userName 等

        // 创建一个缓存用于存放 查询到的结果集
        ConcurrentHashMap<String, Object> cHashMap = new ConcurrentHashMap<>(); // 提高性能先查询缓存在进行查询数据库

        // 进行对该类的字段进行处理 查找存在注解的字段
        for (Field field : fields) {
            NeedSetValue sv = field.getAnnotation(NeedSetValue.class); // 查找那个字段上的带有NeedSetValue注解
            // 判断该字段上是否存在注解 不存在的直接跳过
            if (sv == null){
                continue;
            }
            // 设置私有可见
            field.setAccessible(true);
            // 获取 实际要调用的类(UserDao)
            Object bean = this.applicationContext.getBean(sv.beanClazz());
            // 获取实际调用的方法 sv.method=方法的名字   sv.params = 方法需要的参数
            Method method =sv.beanClazz().getMethod(sv.method(), clazz.getDeclaredField(sv.params()).getType());
            Field paramsField = clazz.getDeclaredField(sv.params());
            paramsField.setAccessible(true);

            Field fieldTarget = null;
            boolean needSetFileValue = !StringUtils.isEmpty(sv.targetFiled());
            // 用于从缓存中获取数据
            String keyPrefix = sv.beanClazz() +"_" + sv.method() + "_" + sv.targetFiled() + "_";
            for (Object o : col) {
                // 判断查询出的bean 是否存在 注解的字段中
                Object paramValue = paramsField.get(o);
                // 获取查询的 另一个dao的参数
                if (paramValue == null){
                    continue;
                }
                String key =  keyPrefix + paramValue;
                Object value;
                if (cHashMap.contains(key)){
                     value = cHashMap.get(key);
                }else{
                    // 通过反射获取 该对象的实际数据 bean : class(实际调用用户的类) paramValue : 实际的参数
                     value = method.invoke(bean, paramValue);
                     if (needSetFileValue){
                         if (value != null){
                             if (fieldTarget == null){
                                 fieldTarget = value.getClass().getDeclaredField(sv.targetFiled());
                                 fieldTarget.setAccessible(true);
                             }
                             value = fieldTarget.get(value);
                         }
                     }
                     // 放入缓存
                    cHashMap.put(key, value);
                }
                field.set(o, value);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (this.applicationContext == null){
            this.applicationContext = applicationContext;
        }

    }
}
