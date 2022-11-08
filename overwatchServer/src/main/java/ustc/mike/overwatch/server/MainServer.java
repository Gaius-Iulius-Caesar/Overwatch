

package ustc.mike.overwatch.server;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import ustc.mike.overwatch.server.net.NettyServer;

@SpringBootApplication
@Component
public class MainServer {

    
    public static void main(String[] args) {
        Object[] classes = new Object[2];
        classes[0] = MainServer.class;
        classes[1] = NettyServer.class;
    
        SpringApplication.run(classes, args);
    }
}
