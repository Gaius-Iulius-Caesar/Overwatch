

package ustc.mike.overwatch.server;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ustc.mike.overwatch.server.net.NettyServer;

@SpringBootApplication
@Component
public class MainServer {
    
//    @Value("${overwatch.register.port:9090}")
//    int    port;
//    @Value("${overwatch.register.ip:127.0.0.1}")
//    String ip;
//
//    private Server server = new Server();

//    @PostConstruct
//    public void start() throws InterruptedException, IOException {
//        server.setIp(InetAddress.getLocalHost().getHostAddress());
//        System.out.println(ip + port);
//        Socket socket = new Socket(ip, port);
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        Command command = new Command();
//        command.setType(CommandType.SERVER_ONLINE);
//        HashMap<String, String> contents = new HashMap<String, String>();
//        contents.put("ip", server.getIp());
//        command.setContents(contents);
//
//        writer.write(command.toString());
//        writer.flush();
//    }
    
    public static void main(String[] args) {
        Object[] classes = new Object[2];
        classes[0] = MainServer.class;
        classes[1] = NettyServer.class;
    
        SpringApplication.run(classes, args);
    }
}
