package com.example.server.entity;

import lombok.Data;


/**
 * 用户信息的实体类
 *
 * @author Wu Sai
 * @version 1.00
 * @Date 2022.10.29
 */
@Data
public class User {
    private String username;
    private String password;
}
