package com.fengkun.angel.netty.serial; 

import java.io.Serializable;

/**
 * 
* <p>Title: RequestBean.java<／p>
* <p>Description: <／p>
* @author boyxiaokun
* @date 2018年4月15日
* @version 1.0
* 客户端请求数据
 */
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String requestMessage;
	private byte[] attachment;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	
	
	
}
 