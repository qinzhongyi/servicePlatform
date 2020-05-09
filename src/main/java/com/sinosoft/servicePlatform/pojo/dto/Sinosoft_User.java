package com.sinosoft.servicePlatform.pojo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class Sinosoft_User {

	public Sinosoft_User(){}
	
	private String UserCode;
    private String UserName;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date CreateDate = new Date();
	@Id
    private String UserId;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date UpdateDate;
    private String UserPwd;
    
	public String getUserCode() {
		return UserCode;
	}
	public void setUserCode(String userCode) {
		UserCode = userCode;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
	public String getUserPwd() {
		return UserPwd;
	}
	public void setUserPwd(String userPwd) {
		UserPwd = userPwd;
	}
    
    
}
