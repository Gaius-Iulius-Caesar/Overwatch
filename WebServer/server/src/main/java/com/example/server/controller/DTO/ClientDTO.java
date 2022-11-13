package com.example.server.controller.DTO;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientDTO {
    private String ip;
    private String name;
    public String toString() {
        return JSON.toJSONString(this) + "\n";
    }

}
