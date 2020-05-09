package com.sinosoft.servicePlatform.pojo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class SinosoftSharedBuffer {
	
	public SinosoftSharedBuffer(){}
	
	private String userCode;

	@Id
	private String token;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date loginDate = new Date();
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	
	private String validated;
	
	private String ip;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
