package com.sinosoft.servicePlatform.pojo.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 请求入参头信息
 * 文件名字： ReqHead
 * @author 作者：qinzy
 * 功能描述：1.
 * 创建时间: 2018年7月6日 下午5:20:33
 * 所属公司: sinosoft
 */
public class ReqHeadVo {
	
	public ReqHeadVo(String transCode){
		this.transCode = transCode;
	}
	public ReqHeadVo(){}
	
	private String token;
	@JSONField(format="yyyy-MM-dd HH:mm:ss.sss")
	private Date date;
	
	private String transCode;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTransCode() {
		return transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

}
