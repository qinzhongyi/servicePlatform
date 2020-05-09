package com.sinosoft.servicePlatform.pojo.vo;

import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;

public class UserReqVo {
	
	private ReqHeadVo head;
	
	private Sinosoft_User user;
	
	private String ip;

	public ReqHeadVo getHead() {
		return head;
	}

	public void setHead(ReqHeadVo head) {
		this.head = head;
	}

	public Sinosoft_User getUser() {
		return user;
	}

	public void setUser(Sinosoft_User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
