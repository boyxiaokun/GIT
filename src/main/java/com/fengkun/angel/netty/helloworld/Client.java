package com.xiaokun.netty.helloworld; 

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p>Title: Client.java<／p>
 * <p>Description: <／p>
 * @author boyxiaokun
 * @date 2018年4月13日
 * @version 1.0
 * 类说明
 */
public class Client {
	
	public static void main(String[] args) throws Exception {
		
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
				 .channel(NioSocketChannel.class)
				 .handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel sc)
							throws Exception {
						sc.pipeline().addLast(new ClientHandler());
					}
				});
		
		ChannelFuture future = bootstrap.connect("127.0.0.1",7654).sync();
		
		future.channel().writeAndFlush(Unpooled.copiedBuffer("333".getBytes()));
		future.channel().closeFuture().sync();
		
		group.shutdownGracefully();
	}
}





















 