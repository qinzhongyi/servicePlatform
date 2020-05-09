package com.sinosoft.servicePlatform.service;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftProject;
import com.sinosoft.servicePlatform.pojo.vo.ProjectReqVo;

public interface ProjectService {
	
	public void del(ProjectReqVo vo) throws Exception;
	
	public SinosoftProject query(ProjectReqVo vo);
	
	public void update(ProjectReqVo vo) throws Exception;

	public List<SinosoftProject> sel(ProjectReqVo vo);

}
