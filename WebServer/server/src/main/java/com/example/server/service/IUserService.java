package com.example.server.service;

import com.example.server.entity.User;

public interface IUserService {
    boolean login(User user);

    boolean register(User user);
}
