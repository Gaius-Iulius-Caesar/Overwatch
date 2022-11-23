
package com.overwatch.dataServer.net;

import com.alibaba.fastjson.JSON;
import com.overwatch.dataServer.dao.ClientMapper;
import com.overwatch.dataServer.dao.RecordMapper;
import com.overwatch.dataServer.dao.ResultMapper;
import com.overwatch.dataServer.utils.SpringContextUtil;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import ustc.mike.overwatch.common.data.*;


import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class SelectHandler extends SimpleChannelHandler {



    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Value("dataServer.ip")
    private String localHostIp;
    public SelectHandler() throws IOException {
    }

    private Socket socket ;
    private BufferedReader reader;
    private BufferedWriter writer ;

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws IOException {
        ApplicationContext CTX= SpringContextUtil.getApplicationContext();
        recordMapper=CTX.getBean(RecordMapper.class);
        resultMapper=CTX.getBean(ResultMapper.class);
        clientMapper=CTX.getBean(ClientMapper.class);
        Command command = JSON.parseObject(e.getMessage().toString(), Command.class);
        Channel channel = ctx.getChannel();
        InetSocketAddress ipSocket = (InetSocketAddress)channel.getRemoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        System.out.print("LocalHostIp: " +CTX.getEnvironment().getProperty("dataServer.ip")+" 收到了来自"+clientIp+"的信息："+command);

        switch (command.getType()) {
            case CommandType.SELECT_ALL: {
                List<Result> list=resultMapper.selectAll();
                if(list!=null)
                {
                    Long now=System.currentTimeMillis();
                    for(Result result:list)
                    {
                        if(now<=result.getTimeStamp()+50000)
                        {
                            result.setStatus(true);
                        }
                    }
                }
                channel.write(JSON.toJSONString(list)+"\n");
                break;
            }
            case CommandType.SELECT_ALLUPTODATE: {
                List<Result> list=resultMapper.selectAllUpToDate();
                if(list!=null)
                {
                    Long now=System.currentTimeMillis();
                    for(Result result:list)
                    {
                        if(now<=result.getTimeStamp()+50000)
                        {
                            result.setStatus(true);
                        }
                    }
                }
                channel.write(JSON.toJSONString(list)+'\n');
                break;
            }
            case CommandType.INSERT:{
                Report report = JSON.parseObject(command.getContents().get("report"), Report.class);
                Record record = new Record();
                record.setAvgLoad(report.getLoad());
                record.setCpuNum(report.getCpus());
                record.setIp(report.getIp());
                record.setTimeStamp(System.currentTimeMillis());
                recordMapper.insertRecord(record);
                break;
            }
            case CommandType.INSERT_CLIENT:{
                Client client=JSON.parseObject(command.getContents().get("client"), Client.class);
                clientMapper.insertClient(client);
                break;
            }
            case CommandType.TEST:{
                String response="dataServer responser"+"\n";
                channel.write(response);
                break;
            }

        }
    }
    
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Client client = (Client) ctx.getAttachment();
        if (client != null) {
            System.out.println(client.getName() + " offline!");
        }
        super.channelClosed(ctx, e);
    }
    
    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.writeRequested(ctx, e);
    }
    
    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        super.handleUpstream(ctx, e);
    }
    
    @Override
    public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        super.handleDownstream(ctx, e);
    }
    
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println(e.toString());
        e.getCause().printStackTrace();
    }
    
}
