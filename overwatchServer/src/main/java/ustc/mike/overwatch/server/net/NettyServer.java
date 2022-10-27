
package ustc.mike.overwatch.server.net;



import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


@Component
public class NettyServer implements ApplicationContextAware {
    
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    
    @Value("${send_receive_port}")
    int port;
    private ChannelFactory factory;


    @PostConstruct
    public void start() throws InterruptedException {
        logger.info("begin to start server at " + port);
        
        factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        Timer timer = new HashedWheelTimer();
        
        bootstrap.setPipelineFactory(new MyPipelineFactory(timer));
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(port));
        
        logger.info("server listening on port " + port + " and ready for connections...");
    }
    
    @PreDestroy
    public void stop() {
        logger.info("destroy server resources");
        if (factory != null)
            factory.shutdown();
        logger.info("destroy server resources done");
    }

    public void setApplicationContext(ApplicationContext ctx) throws BeansException { }
}
