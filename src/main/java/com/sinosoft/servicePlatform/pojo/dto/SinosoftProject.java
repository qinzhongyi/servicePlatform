package com.sinosoft.servicePlatform.pojo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
public class SinosoftProject {
	
	public SinosoftProject(){}

	@Id
	private String PId;
    private String PName;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date CreateDate = new Date();
    
    private String CUserCode;
    private String CUserName;
    
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date UpdateDate;
    
    private String Valied;
    private String PRemark;
    private String PAddr;
	private String EId;
	public String getPId() {
		return PId;
	}
	public void setPId(String pId) {
		PId = pId;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	public String getCUserCode() {
		return CUserCode;
	}
	public void setCUserCode(String cUserCode) {
		CUserCode = cUserCode;
	}
	public String getCUserName() {
		return CUserName;
	}
	public void setCUserName(String cUserName) {
		CUserName = cUserName;
	}
	public Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}
	public String getValied() {
		return Valied;
	}
	public void setValied(String valied) {
		Valied = valied;
	}
	public String getPRemark() {
		return PRemark;
	}
	public void setPRemark(String pRemark) {
		PRemark = pRemark;
	}
	public String getPAddr() {
		return PAddr;
	}
	public void setPAddr(String pAddr) {
		PAddr = pAddr;
	}
	public String getEId() {
		return EId;
	}
	public void setEId(String eId) {
		EId = eId;
	}
    
}
