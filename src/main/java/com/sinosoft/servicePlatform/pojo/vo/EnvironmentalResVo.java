package com.sinosoft.servicePlatform.pojo.vo;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;

public class EnvironmentalResVo{
	
	private ResHeadVo head;
	
	private List<SinosoftEnvironmental> envList;

	public List<SinosoftEnvironmental> getEnv() {
		return envList;
	}

	public void setEnv(List<SinosoftEnvironmental> envList) {
		this.envList = envList;
	}

	public ResHeadVo getHead() {
		return head;
	}

	public void setHead(ResHeadVo head) {
		this.head = head;
	}
	
}
