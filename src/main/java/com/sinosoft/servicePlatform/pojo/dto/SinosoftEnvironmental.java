package com.sinosoft.servicePlatform.pojo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 环境配置表
 * 文件名字： SinosoftEnvironmentalDto
 * @author 作者：qinzy
 * 功能描述：1.
 * 创建时间: 2018年7月6日 下午4:35:39
 * 所属公司: sinosoft
 */
@Entity
public class SinosoftEnvironmental {

	public SinosoftEnvironmental(){};
	
	/**
	 * 环境编码
	 */
	@Id
	private String eId;
	/**
	 * 环境名称
	 */
	private String eName;
	/**
	 * 配置时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createDate = new Date();
	/**
	 * 配置人编码
	 */
	private String cUserCode;
	/**
	 * 配置人名称
	 */
	private String cUserName;
	/**
	 * 是否有效 1有效 0无效
	 */
	private String valied;
	/**
	 * 更新时间
	 */
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	/**
	 * 环境说明
	 */
	private String eRemark;
	
	public String geteId() {
		return eId;
	}
	public void seteId(String eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getcUserCode() {
		return cUserCode;
	}
	public void setcUserCode(String cUserCode) {
		this.cUserCode = cUserCode;
	}
	public String getcUserName() {
		return cUserName;
	}
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	public String getValied() {
		return valied;
	}
	public void setValied(String valied) {
		this.valied = valied;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String geteRemark() {
		return eRemark;
	}
	public void seteRemark(String eRemark) {
		this.eRemark = eRemark;
	}
	
	
}
