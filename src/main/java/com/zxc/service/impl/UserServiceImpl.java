package com.zxc.service.impl;

import com.zxc.dao.UserDao;
import com.zxc.dao.impl.UserDaoImpl;
import com.zxc.pojo.User;
import com.zxc.service.UserService;

/**
 * @author zhu
 * @create 2021-08-20 20:31
 */
public class UserServiceImpl implements UserService {
     private UserDao userDao=new UserDaoImpl();


    @Override
    public void registUser(User user) {
            userDao.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String name) {
        if(userDao.queryUserByUsername(name)!=null){
            return true;
        }
        return false;
    }
}
