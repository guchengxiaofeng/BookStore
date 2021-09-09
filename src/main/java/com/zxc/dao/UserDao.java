package com.zxc.dao;

import com.zxc.pojo.User;

/**
 * @author zhu
 * @create 2021-08-20 16:22
 */
public interface UserDao {
     User queryUserByUsername(String name);
     User queryUserByUsernameAndPassword(String name,String password);
     void saveUser(User user);
}

