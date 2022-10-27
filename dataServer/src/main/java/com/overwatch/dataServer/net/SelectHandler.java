
package com.overwatch.dataServer.net;

import com.alibaba.fastjson.JSON;
import com.overwatch.dataServer.dao.RecordMapper;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ustc.mike.overwatch.common.data.*;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class SelectHandler extends SimpleChannelHandler {


    @Autowired
    private RecordMapper recordMapper;


    public SelectHandler() throws IOException {
    }

    private Socket socket ;
    private BufferedReader reader;
    private BufferedWriter writer ;

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws IOException {
        Command command = JSON.parseObject(e.getMessage().toString(), Command.class);
        System.out.print(command);
        String ip=command.getContents().get("ip");
        int port=Integer.parseInt(command.getContents().get("port"));
        socket=new Socket(ip,port);
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Channel channel = ctx.getChannel();
        switch (command.getType()) {
            case CommandType.SELECT_ALL: {
                List<Record> list=recordMapper.selectAll();
                channel.write(JSON.toJSONString(list));
                break;
            }
            case CommandType.SELECT_ALLUPTODATE: {
                List<Record> list=recordMapper.selectAllUpToDate();
                channel.write(JSON.toJSONString(list));
                break;
            }
            case CommandType.SELECT_ONEUPTODATE:{
                Record record = recordMapper.selectOneUpToDate(command.getContents().get("name"));
                channel.write(JSON.toJSONString(record.toString()));
                break;
            }
            case CommandType.INSERT:{
                Report report = JSON.parseObject(command.getContents().get("report"), Report.class);
                Record record = new Record();
                record.setAvgLoad(report.getLoad());
                record.setCpuNum(report.getCpus());
                record.setName(report.getName());
                record.setTimeStamp(System.currentTimeMillis());
                record.setOs(report.getOS());
                recordMapper.insertRecord(record);
                break;
            }
            case CommandType.TEST:{
                String response="dataServer responser"+"\n";
                channel.write(response);
            }
        }
        socket.close();
        reader.close();
        writer.close();
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
