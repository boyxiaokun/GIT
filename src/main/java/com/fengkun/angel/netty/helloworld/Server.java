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
 * <p>Title: Server.java<��p>
 * <p>Description: <��p>
 * @author boyxiaokun
 * @date 2018��4��13��
 * @version 1.0
 * ��˵��
 */
public class Server {

	public static void main(String[] args) throws Exception {
		//1 ��һ���߳��� ��������Client������
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//2�ڶ����߳��� ������������ͨ�ŵģ������д�ģ�
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		//3����һ��������Bootstrap�� ��Server��������
		ServerBootstrap bootstrap = new ServerBootstrap();
		//�������߳������
		bootstrap.group(bossGroup, workerGroup)
				 //��Ҫָ��ʹ��NioServerSocketChannel�������͵�ͨ����NIO��ģʽ��
				 .channel(NioServerSocketChannel.class)
				 .option(ChannelOption.SO_BACKLOG, 1024)//����tcp������
				 .option(ChannelOption.SO_SNDBUF, 1024*1024)//���÷��ͻ����С
				 .option(ChannelOption.SO_RCVBUF, 1024*1024)//���ý��ջ����С
				 .option(ChannelOption.SO_KEEPALIVE, true)//��������
				 //һ��Ҫʹ�� childHandler ȥ�󶨾���� �¼�������
				 .childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						//���þ������ݽ��շ����Ĵ���
						sc.pipeline().addLast(new ServerHandler());
					}
					 
				});
		//��ָ���Ķ˿� ���м���
		ChannelFuture future = bootstrap.bind(7654).sync();
		
		//����
		future.channel().closeFuture().sync();
		
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		
	}
}
 
























