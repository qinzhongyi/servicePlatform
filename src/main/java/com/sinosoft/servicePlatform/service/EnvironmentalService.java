package com.sinosoft.servicePlatform.service;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;
import com.sinosoft.servicePlatform.pojo.vo.EnvironmentalReqVo;

public interface EnvironmentalService {
	
	public void del(EnvironmentalReqVo vo) throws Exception;
	
	public SinosoftEnvironmental query(EnvironmentalReqVo vo);
	
	public List<SinosoftEnvironmental> update(EnvironmentalReqVo vo) throws Exception;

	public List<SinosoftEnvironmental> sel(EnvironmentalReqVo vo);

}
