package com.ustc.overwatch;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;



import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ustc.mike.overwatch.common.data.Command;
import ustc.mike.overwatch.common.data.CommandType;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController  {


    private Socket socket ;
    private BufferedReader reader;
    private BufferedWriter writer ;

    @Value("${send_receive_port}")
    private int dataServer_port;
    @Value("${dataServer.ip}")
    private String dataServer_ip;

    @Value("${webServer.ip}")
    private String webServer_ip;
    @Value("${webServer.port}")
    private int webSever_port;
    //index页面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String home(Map<String, Object> model) {
        return "login";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //登录方法
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username："+username+" password:"+password);
        if ( username.equals("admin") && password.equals("123456")){
            System.out.println("index");
//            return "blank";
//            map.put("name","admin");
            return "redirect:/index";
        } else {
            System.out.println("login");
            return "redirect:/login";
        }
    }

    @RequestMapping("/tables")
    public String tables(Map<String, Object> model) {
        return "tables";
    }

    @RequestMapping("/dynamic")
    public String dynamic(Map<String, Object> model) {
        return "data_dynamic";
    }

    @RequestMapping("/history")
    public String history(Map<String, Object> model) {
        return "data_history";
    }

    @RequestMapping("/config")
    public String config(Map<String, Object> model) {
        return "config";
    }


    @RequestMapping("/test")
    @ResponseBody
    public String Test() throws IOException {
        socket=new Socket(dataServer_ip,dataServer_port);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.TEST);
        HashMap<String, String> contents = new HashMap<String, String>();
		contents.put("ip",webServer_ip);
		contents.put("port",String.valueOf(webSever_port));
        contents.put("test","webserver send");
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response=reader.readLine();
        System.out.println(response);
        socket.close();
        reader.close();
        writer.close();
        return response;
    }



    @RequestMapping("/获取所有的机器最新的状态")
    @ResponseBody
    public String getAllUpToDate() throws IOException {
        socket=new Socket(dataServer_ip,dataServer_port);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ALLUPTODATE);
        command.getContents().put("ip",dataServer_ip);
        command.getContents().put("port",String.valueOf(dataServer_port));
        writer.write(command.toString());
        String response;
        while((response=reader.readLine())==null)
        {
            if(response!=null)
                break;
        }
        socket.close();
        reader.close();
        writer.close();
        return response;
    }

    @RequestMapping("/获取一个的机器最新的状态")
    @ResponseBody
    public String getOneUpToDate(@RequestParam("name")String name) throws IOException {
        socket=new Socket(dataServer_ip,dataServer_port);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ONEUPTODATE);
        HashMap<String, String> contents = new HashMap<String, String>();
        contents.put("ip",webServer_ip);
        contents.put("port",String.valueOf(webSever_port));
        contents.put("name",name);
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response;
        while((response=reader.readLine())!=null)
        {
            if(response!=null)
                break;
        }
        socket.close();
        reader.close();
        writer.close();
        return response;
    }
    @RequestMapping("/获取所有机器的所有状态")
    @ResponseBody
    public String getAll() throws IOException {
        socket=new Socket(dataServer_ip,dataServer_port);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Command command=new Command();
        command.setType(CommandType.SELECT_ALL);
        HashMap<String, String> contents = new HashMap<String, String>();
        contents.put("ip",webServer_ip);
        contents.put("port",String.valueOf(webSever_port));
        command.setContents(contents);
        writer.write(command.toString());
        writer.flush();
        String response;
        while((response=reader.readLine())!=null)
        {
            if(response!=null)
                break;
        }
        socket.close();
        reader.close();
        writer.close();
        return response;
    }
}
