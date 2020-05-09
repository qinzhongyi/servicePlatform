package com.sinosoft.servicePlatform.pojo.vo;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;

public class EnvironmentalReqVo{
	
	private ReqHeadVo head;
	
	private SinosoftEnvironmental env;

	public SinosoftEnvironmental getEnv() {
		return env;
	}

	public void setEnv(SinosoftEnvironmental env) {
		this.env = env;
	}

	public ReqHeadVo getHead() {
		return head;
	}

	public void setHead(ReqHeadVo head) {
		this.head = head;
	}
	
}
