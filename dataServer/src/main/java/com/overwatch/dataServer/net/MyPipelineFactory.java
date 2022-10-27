
package com.overwatch.dataServer.net;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
import org.jboss.netty.util.Timer;

import java.io.IOException;


public class MyPipelineFactory implements ChannelPipelineFactory {
    
    private final  Timer          timer;
    private static ChannelHandler idleStateHandler;
    
    public MyPipelineFactory(Timer t) {
        this.timer = t;
    }
    
    public ChannelPipeline getPipeline() throws IOException {
        // create default pipeline from static method
        ChannelPipeline pipeline = Channels.pipeline();
        
        // Decoders
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("handler", new SelectHandler());
        
        // Encoders
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        return pipeline;
    }
}
