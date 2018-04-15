package com.xiaokun.netty.serial; 

import java.io.Serializable;

/**
 * <p>Title: ResponseBean.java<／p>
 * <p>Description: <／p>
 * @author boyxiaokun
 * @date 2018年4月15日
 * @version 1.0
 * 类说明
 * 服务端响应数据
 */
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String responseMessage;
	
	
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
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	

}
 