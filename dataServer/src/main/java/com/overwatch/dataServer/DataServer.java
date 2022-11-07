package com.overwatch.dataServer;

import com.overwatch.dataServer.net.NettyServer;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ustc.mike.overwatch.common.data.Record;

@SpringBootApplication
@Component
@EnableAutoConfiguration
public class DataServer {


	public static void main(String[] args) {
		Object[] classes = new Object[2];
		classes[0]= DataServer.class;
		classes[1]= NettyServer.class;
		SpringApplication.run(classes, args);
	}

}
