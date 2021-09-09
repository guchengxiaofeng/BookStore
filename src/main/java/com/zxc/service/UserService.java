package com.zxc.service;

import com.zxc.pojo.User;

/**
 * @author zhu
 * @create 2021-08-20 20:25
 */
public interface UserService {
    void registUser(User user);

    User loginUser(User user);

    boolean existUsername(String name);
}
