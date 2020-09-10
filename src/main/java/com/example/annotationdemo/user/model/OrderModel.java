package com.example.annotationdemo.user.model;


import com.example.annotationdemo.user.annotation.NeedSetValue;
import com.example.annotationdemo.user.dao.UserDao;

public class OrderModel {

    String orderId;
    String userId;
    // userName 需要进行设置用户名字
    @NeedSetValue(beanClazz= UserDao.class, method = "getUser", params = "userId", targetFiled = "userName" )
    String userName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
