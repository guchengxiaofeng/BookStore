package com.zxc.dao.impl;

import com.zxc.dao.BaseDao;
import com.zxc.dao.UserDao;
import com.zxc.pojo.User;

/**
 * @author zhu
 * @create 2021-08-20 16:31
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String name) {
        String sql = "select username from t_user where username=?";
        return queryForOne(User.class, sql, name);

    }

    @Override
    public User queryUserByUsernameAndPassword(String name,String password) {
        String sql = "select id,username,password from t_user where username=? and password=?";
        return  queryForOne(User.class, sql, name,password);
    }

    @Override
    public void saveUser(User user) {
        String sql="insert into t_user(username,password,email)values(?,?,?)";
        update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
