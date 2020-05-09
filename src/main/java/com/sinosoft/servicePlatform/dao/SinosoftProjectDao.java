package com.sinosoft.servicePlatform.dao;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftProject;

public interface SinosoftProjectDao {
	
	public void save(SinosoftProject sp);
	
	public void delete(SinosoftProject sp);
	
	public SinosoftProject queryById(String pId);
	
	public List<SinosoftProject> queryAll(SinosoftProject sp);
	
	public void update(SinosoftProject sp);
}
