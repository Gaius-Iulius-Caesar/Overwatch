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

package ustc.mike.overwatch.client;

import com.alibaba.fastjson.JSON;
import ustc.mike.overwatch.client.utils.Utils;
import ustc.mike.overwatch.common.data.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mike
 * @project overwatch
 * @date 10/12/2017, 3:07 PM
 * @e-mail mike@mikecoder.cn
 */
@SpringBootApplication
@Component
@EnableAutoConfiguration
public class MainClient implements CommandLineRunner {
    @Value("${overwatchServer.port}")
    int    overwatchServerPort;
    @Value("${overwatchServer.ip}")
    String overwatchServerIp;

    
    private Client client = new Client();
    
    @Override
    public void run(String... args) throws Exception {
        Logger logger = LoggerFactory.getLogger(MainClient.class.getName());
        String localIp = InetAddress.getLocalHost().getHostAddress();
    
        client.setName(Utils.genRandomName());
        client.setIp(localIp);
        client.setOnline(true);
    
        logger.info("Register to :" + overwatchServerIp + ":" + overwatchServerPort);//向服务器注册
        Socket socket = new Socket(overwatchServerIp, overwatchServerPort);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    
        Command registCmd = new Command();
        registCmd.setType(CommandType.CLIENT_REGIST);
        HashMap<String, String> registCmdContents = new HashMap<String, String>();
    
        registCmdContents.put("client", client.toString());
        registCmd.setContents(registCmdContents);
    
        writer.write(registCmd.toString());
        writer.flush();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {//心跳协议
                Report report = new Report();
                report.setName(client.getName());
                report.setCpus(Utils.getCpuNum());
                report.setLoad(Utils.getAvgLoad());
                report.setOS(Utils.getOs());
    
                Command reportCmd = new Command();
                reportCmd.setType(CommandType.CLIENT_REPORT);
                HashMap<String, String> reportCmdContents = new HashMap<String, String>();
                reportCmdContents.put("report", report.toString());
                reportCmd.setContents(reportCmdContents);
                
                try {
                    writer.write(reportCmd.toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(MainClient.class);
    }
}
