package com.example.server.controller;


import com.example.server.common.Command;
import com.example.server.common.CommandType;
import com.example.server.common.Constants;
import com.example.server.common.Result;
import com.example.server.controller.DTO.ClientDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

@RequestMapping("/machine")
@RestController
public class MachineController {
    @Value("${dataServer.ip}")
    private String dataServer_ip;

    @Value("${dataServer.port}")
    private int dataServer_port;

    @GetMapping("/getAllUpToDate")
    public Result getAllUpToDate() throws IOException {
        Socket socket = new Socket(dataServer_ip, dataServer_port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ALLUPTODATE);
        writer.write(command.toString());
        writer.flush();
        String response = reader.readLine();


        socket.close();
        reader.close();
        writer.close();
        return Result.success(response);
    }

    @GetMapping("/getOneUpToDate")
    public Result getOneUpToDate(@RequestParam(defaultValue = "") String name) throws IOException {
        if(name.equals(""))
            return Result.error(Constants.CODE_400,"参数错误");
        Socket socket = new Socket(dataServer_ip, dataServer_port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ONEUPTODATE);
        HashMap<String, String> contents = new HashMap<>();
//        contents.put("ip",webServer_ip);
//        contents.put("port",String.valueOf(webSever_port));
        contents.put("name",name);
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response = reader.readLine();
        socket.close();
        reader.close();
        writer.close();
        return Result.success(response);
    }
    @GetMapping("/getAll")
    public Result getAll() throws IOException {
        Socket socket = new Socket(dataServer_ip, dataServer_port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ALL);
        HashMap<String, String> contents = new HashMap<>();
//        contents.put("ip",webServer_ip);
//        contents.put("port",String.valueOf(webSever_port));
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response = reader.readLine();


        socket.close();
        reader.close();
        writer.close();
        return Result.success(response);
    }

    @PostMapping("/addOneClient")
    public Result addOneClient(@RequestBody ClientDTO client) throws IOException {

        Socket socket = new Socket(dataServer_ip, dataServer_port);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Command command = new Command();
        command.setType(CommandType.INSERT_CLIENT);
        HashMap<String, String> reportCmdContents = new HashMap<String, String>();
        reportCmdContents.put("client", client.toString());
        command.setContents(reportCmdContents);
        writer.write(command.toString());
        writer.flush();
        socket.close();
//        reader.close();
        writer.close();
        return Result.success();
    }
}
