package com.fengkun.angel.netty.serial; 

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 
* <p>Title: Server.java<／p>
* <p>Description: <／p>
* @author boyxiaokun
* @date 2018年4月15日
* @version 1.0
 */
public class Server {

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workGroup)
		 .channel(NioServerSocketChannel.class)
		 .option(ChannelOption.SO_BACKLOG, 1024)
		 //设置日志
		 .handler(new LoggingHandler(LogLevel.INFO))
		 .childHandler(new ChannelInitializer<SocketChannel>() {
			protected void initChannel(SocketChannel sc) throws Exception {
				sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
				sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
				sc.pipeline().addLast(new ServerHandler());
			}
		});
		
		ChannelFuture cf = b.bind(7654).sync();
		
		cf.channel().closeFuture().sync();
		bossGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
	}
}
 
























