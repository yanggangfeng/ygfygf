package com.example.annotationdemo.user.controller;


import com.example.annotationdemo.user.dao.OrderDao;
import com.example.annotationdemo.user.dao.UserDao;
import com.example.annotationdemo.user.model.OrderModel;
import com.example.annotationdemo.user.model.UserModel;
import com.example.annotationdemo.user.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
   /* @Autowired
    JdbcTemplate jdbcTemplate;*/

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("user")
    public UserModel getUser(){
//        userDao.getUser("1234561");
//        jdbcTemplate.query("select user_name from USer where user_id = '1234561'", UserModel);
       // Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("select * from USer where user_id = '1234561'");
        UserModel user = userDao.getUser("1234561");
        System.out.println("user.getUserNam1e() = " + user.getUserName());
        orderService.listOrder();
        return user;
    }


    @RequestMapping("user1")
    public List<OrderModel> getUser1(){

        return orderDao.getOrderList();
    }

}
