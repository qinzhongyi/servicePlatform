package com.sinosoft.servicePlatform.pojo.vo;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftProject;

public class ProjectResVo {

	private ResHeadVo head;
	
	private List<SinosoftProject> projectList;

	public ResHeadVo getHead() {
		return head;
	}

	public void setHead(ResHeadVo head) {
		this.head = head;
	}

	public List<SinosoftProject> getProject() {
		return projectList;
	}

	public void setProject(List<SinosoftProject> projectList) {
		this.projectList = projectList;
	}
	
}
