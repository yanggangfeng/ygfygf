package com.example.annotationdemo.user.dao;

import com.example.annotationdemo.user.model.OrderModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

    @Select("select sex as orderId, user_id  as userId from order1")
    List<OrderModel> getOrderList();
}
