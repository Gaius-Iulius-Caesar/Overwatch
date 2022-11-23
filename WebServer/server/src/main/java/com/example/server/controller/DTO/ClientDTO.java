package com.example.server.controller.DTO;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

/**
 * 前后端交互Client信息的传输类
 *
 * @author Wu Sai
 * @Date 2022.10.30
 * @version 1.00
 */
@Data
@ToString
public class ClientDTO {
    private String ip;
    private String name;
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

}
