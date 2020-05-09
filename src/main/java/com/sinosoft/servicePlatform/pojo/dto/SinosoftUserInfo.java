package com.sinosoft.servicePlatform.pojo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class SinosoftUserInfo {

	public SinosoftUserInfo(){}
	@Id
    private String UserID;
    private String Mobile;
    private String EMail;
    private String WeChar;
    private String HeadAddr;
    private String Remark;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date CreateDate = new Date();
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date UpdateDate;
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getWeChar() {
		return WeChar;
	}
	public void setWeChar(String weChar) {
		WeChar = weChar;
	}
	public String getHeadAddr() {
		return HeadAddr;
	}
	public void setHeadAddr(String headAddr) {
		HeadAddr = headAddr;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
    
}
