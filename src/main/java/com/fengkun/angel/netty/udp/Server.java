package com.fengkun.angel.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
* <p>Title: Server.java<／p>
* <p>Description: <／p>
* @author boyxiaokun
* @date 2018年4月15日
* @version 1.0
* UDP通信
 */
public class Server {
    public void run(int port) throws Exception {
    	EventLoopGroup group = new NioEventLoopGroup();
		try {
		    Bootstrap b = new Bootstrap();
		    b.group(group).channel(NioDatagramChannel.class)
			    .option(ChannelOption.SO_BROADCAST, true)
			    .handler(new ServerHandler());
		    b.bind(port).sync().channel().closeFuture().await();
		} finally {
		    group.shutdownGracefully();
		}
    }

    public static void main(String[] args) throws Exception {
		new Server().run(8765);
		new Server().run(8764);
    }
}
