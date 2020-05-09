package com.sinosoft.servicePlatform.pojo.vo;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftUserInfo;

public class UserInfoResVo {
	
	private ResHeadVo head;
	
	private List<SinosoftUserInfo> userInfoList;

	public ResHeadVo getHead() {
		return head;
	}

	public void setHead(ResHeadVo head) {
		this.head = head;
	}

	public List<SinosoftUserInfo> getUserInfo() {
		return userInfoList;
	}

	public void setUserInfo(List<SinosoftUserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}
	
	
}
