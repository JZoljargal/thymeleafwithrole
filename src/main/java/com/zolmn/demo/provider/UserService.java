package com.zolmn.demo.provider;

import com.zolmn.demo.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
