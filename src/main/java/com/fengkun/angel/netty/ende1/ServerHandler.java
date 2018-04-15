package com.fengkun.angel.netty.ende1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
* <p>Title: ServerHandler.java<／p>
* <p>Description: <／p>
* @author boyxiaokun
* @date 2018年4月15日
* @version 1.0
 */
public class ServerHandler extends ChannelHandlerAdapter {


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(" server channel active... ");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String request = (String)msg;
		System.out.println("Server :" + request);
		String response = "响应$_";
		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
		ctx.close();
	}




}
