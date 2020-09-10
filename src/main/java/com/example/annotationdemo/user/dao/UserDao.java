package com.example.annotationdemo.user.dao;


import com.example.annotationdemo.user.model.UserModel;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Select(" SELECT user_id as userId, user_name as userName, sex FROM User where user_id= #{userId}")
    UserModel getUser(String userId);

}
