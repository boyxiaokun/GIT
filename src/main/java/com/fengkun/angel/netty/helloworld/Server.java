package com.xiaokun.netty.helloworld; 

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>Title: Server.java<／p>
 * <p>Description: <／p>
 * @author boyxiaokun
 * @date 2018年4月13日
 * @version 1.0
 * 类说明
 */
public class Server {

	public static void main(String[] args) throws Exception {
		//1 第一个线程组 用来接收Client端连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//2第二个线程组 用来进行网络通信的（网络读写的）
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		//3创建一个辅助类Bootstrap， 对Server进行配置
		ServerBootstrap bootstrap = new ServerBootstrap();
		//把两个线程组加入
		bootstrap.group(bossGroup, workerGroup)
				 //我要指定使用NioServerSocketChannel这种类型的通道（NIO的模式）
				 .channel(NioServerSocketChannel.class)
				 .option(ChannelOption.SO_BACKLOG, 1024)//设置tcp缓冲区
				 .option(ChannelOption.SO_SNDBUF, 1024*1024)//设置发送缓冲大小
				 .option(ChannelOption.SO_RCVBUF, 1024*1024)//设置接收缓冲大小
				 .option(ChannelOption.SO_KEEPALIVE, true)//保持连接
				 //一定要使用 childHandler 去绑定具体的 事件处理器
				 .childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						//设置具体数据接收方法的处理
						sc.pipeline().addLast(new ServerHandler());
					}
					 
				});
		//绑定指定的端口 进行监听
		ChannelFuture future = bootstrap.bind(7654).sync();
		
		//阻塞
		future.channel().closeFuture().sync();
		
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		
	}
}
 
























