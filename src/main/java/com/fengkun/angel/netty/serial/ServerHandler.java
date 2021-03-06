package com.fengkun.angel.netty.serial; 

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import com.fengkun.angel.netty.utils.GzipUtils;

/**
 * 
* <p>Title: ServerHandler.java<／p>
* <p>Description: <／p>
* @author boyxiaokun
* @date 2018年4月15日
* @version 1.0
 */
public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		RequestBean requestBean = (RequestBean)msg;
		System.out.println("Server :" + requestBean.getId() + ", " + requestBean.getName()+", "+requestBean.getRequestMessage());
		byte[] attachment = GzipUtils.ungzip(requestBean.getAttachment());
		String readPath = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + UUID.randomUUID().toString()  + ".jpg";
		FileOutputStream fos = new FileOutputStream(readPath);
		fos.write(attachment);
		fos.close();
		
		ResponseBean responseBean = new ResponseBean();
		responseBean.setId(requestBean.getId());
		responseBean.setName(requestBean.getName());
		responseBean.setResponseMessage(requestBean.getRequestMessage());
		ctx.writeAndFlush(responseBean);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
 















