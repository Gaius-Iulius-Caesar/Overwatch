package com.ustc.overwatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import ustc.mike.overwatch.common.data.Command;
import ustc.mike.overwatch.common.data.CommandType;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

@SpringBootApplication
public class OverwatchApplication {

	@Value("${send_receive_port}")
	int    port;
	@Value("${dataServer.ip}")
	String ip;

//	@PostConstruct
//	public void start() throws InterruptedException, IOException {
//		System.out.println(ip + port);
//		Socket socket = new Socket(ip, port);
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		Command command = new Command();
//		command.setType(CommandType.TEST);
//		HashMap<String, String> contents = new HashMap<String, String>();
//		contents.put("ip",ip);
//		contents.put("port","10640");
//		command.setContents(contents);
//
//		writer.write(command.toString());
//		writer.flush();
//		socket.close();
//		writer.close();
//	}

	public static void main(String[] args) {
		SpringApplication.run(OverwatchApplication.class, args);
	}
}
