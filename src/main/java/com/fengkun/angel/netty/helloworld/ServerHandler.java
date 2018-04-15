package com.xiaokun.netty.helloworld; 

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * <p>Title: ServerHandler.java<��p>
 * <p>Description: <��p>
 * @author boyxiaokun
 * @date 2018��4��13��
 * @version 1.0
 * ��˵��
 */
public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		ByteBuf buf = (ByteBuf)msg;
		byte[] data = new byte[buf.readableBytes()];
		buf.readBytes(data);
		String request = new String(data, "utf-8");
		System.out.println("Server :"+ request);
		//��Ӧ�ͻ���
		String response = "��Ӧ�ͻ���";
		ctx.writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
 















