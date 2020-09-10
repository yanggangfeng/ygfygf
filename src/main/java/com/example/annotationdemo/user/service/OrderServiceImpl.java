package com.example.annotationdemo.user.service;

import com.example.annotationdemo.user.annotation.NeedSetFiledValue;
import com.example.annotationdemo.user.dao.OrderDao;
import com.example.annotationdemo.user.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderDao orderDao;

    // 只要用这个注解进行标识 就要进行切面编程
    @NeedSetFiledValue
    public List<OrderModel> listOrder(){
        List<OrderModel> orderList = orderDao.getOrderList();

        return orderList;
    }

}
