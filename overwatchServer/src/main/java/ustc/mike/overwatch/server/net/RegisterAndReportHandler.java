/*******************************************************************************
 * Copyright © 2016 TangDongxin
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 ******************************************************************************/

package ustc.mike.overwatch.server.net;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import ustc.mike.overwatch.common.data.*;


import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class RegisterAndReportHandler extends SimpleChannelHandler {
    
    private Logger logger = LoggerFactory.getLogger(RegisterAndReportHandler.class.getName());

    @Value("${dataServerPort}")
    private int   dataServerPort;
    @Value("${dataServerIp}")
    private String dataServerIp;
    @Value("${overwatchServerPort}")
    private int   overwatchServerPort;
    @Value("${overwatchServerIp}")
    private String overwatchServerIp;
    @Value("${webServerPort}")
    private int   webServerPort;
    @Value("${overwatchServerIp}")
    private String webServerIp;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;


    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws IOException {
        Command command = JSON.parseObject(e.getMessage().toString(), Command.class);
        System.out.println(command);
        switch (command.getType()) {
            case CommandType.CLIENT_REPORT: {//相当于转发Report
                Client client = (Client) ctx.getAttachment();
                if (client == null) {
                    e.getChannel().close();
                }

                command.setType(CommandType.INSERT);

                socket= new Socket(dataServerIp, dataServerPort);
                reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write(command.toString());
                writer.flush();
                socket.close();
                reader.close();
                writer.close();
                break;
            }
        }
    }
    
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Client client = (Client) ctx.getAttachment();
        if (client != null) {
            logger.info(client.getName() + " offline!");
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
