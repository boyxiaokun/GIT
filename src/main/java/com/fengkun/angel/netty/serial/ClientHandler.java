package com.xiaokun.netty.serial; 

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * <p>Title: ClientHandler.java<��p>
 * <p>Description: <��p>
 * @author boyxiaokun
 * @date 2018��4��13��
 * @version 1.0
 * ��˵��
 */
public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try {
			ResponseBean responseBean = (ResponseBean)msg;
			System.out.println("Client :" + responseBean.getId() + ", "+ responseBean.getName() + ", " + responseBean.getResponseMessage() );
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
 

















