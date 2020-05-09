package com.sinosoft.servicePlatform.pojo.vo;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;

public class UserResVo {
	
	private ResHeadVo head;
	
	private List<Sinosoft_User> userList;
	
	private String token;

	public ResHeadVo getHead() {
		return head;
	}

	public void setHead(ResHeadVo head) {
		this.head = head;
	}

	public List<Sinosoft_User> getUserList() {
		return userList;
	}

	public void setUserList(List<Sinosoft_User> userList) {
		this.userList = userList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
