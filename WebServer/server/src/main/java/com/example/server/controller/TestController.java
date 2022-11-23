package com.example.server.controller;

import com.example.server.common.Command;
import com.example.server.common.CommandType;
import com.example.server.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * 前端机器信息增删改查的控制类（测试用）
 *
 * @author Wu Sai
 * @version 1.00
 * @Date 2022.10.30
 */
@RestController
public class TestController {
    @Value("${dataServer.ip}")
    private String dataServer_ip;

    @Value("${dataServer.port}")
    private int dataServer_port;

    @GetMapping("/test")
    public Result Test() throws IOException {
        Socket socket = new Socket(dataServer_ip, dataServer_port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command = new Command();
        command.setType(CommandType.TEST);
        HashMap<String, String> contents = new HashMap<>();
//        contents.put("ip", webServer_ip);
//        contents.put("port", String.valueOf(webSever_port));
        contents.put("test", "webserver send");
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response = reader.readLine();
        System.out.println(response);
        socket.close();
        reader.close();
        writer.close();
        return Result.success(response);
    }
}
