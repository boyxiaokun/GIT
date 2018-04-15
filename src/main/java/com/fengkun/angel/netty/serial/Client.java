package com.xiaokun.netty.serial; 

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.xiaokun.netty.utils.GzipUtils;

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
						sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
						sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
						sc.pipeline().addLast(new ClientHandler());
					}
				});
		
		ChannelFuture future = bootstrap.connect("127.0.0.1",7654).sync();
		
		for (int i = 0; i < 10; i++) {
			RequestBean requestBean = new RequestBean();
			requestBean.setId("feng"+i);
			requestBean.setName("kun"+i);
			requestBean.setRequestMessage("angel"+i);
			
			String readPath = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "001.jpg";
			File file = new File(readPath);
			FileInputStream in = new FileInputStream(file);
			byte[] data = new byte[in.available()];
			in.read(data);
			in.close();
			requestBean.setAttachment(GzipUtils.gzip(data));
			
			future.channel().writeAndFlush(requestBean);
		}
		
		future.channel().closeFuture().sync();
		
		group.shutdownGracefully();
	}
}





















 