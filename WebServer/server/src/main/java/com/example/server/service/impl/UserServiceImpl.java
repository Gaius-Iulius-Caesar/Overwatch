package com.example.server.service.impl;

import com.example.server.entity.User;
import com.example.server.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public boolean login(User user) {
        return true;
    }
}
