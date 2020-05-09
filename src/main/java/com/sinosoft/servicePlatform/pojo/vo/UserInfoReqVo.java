package com.sinosoft.servicePlatform.pojo.vo;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftUserInfo;

public class UserInfoReqVo {
	
	private ReqHeadVo head;
	
	private SinosoftUserInfo userInfo;

	public ReqHeadVo getHead() {
		return head;
	}

	public void setHead(ReqHeadVo head) {
		this.head = head;
	}

	public SinosoftUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SinosoftUserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
