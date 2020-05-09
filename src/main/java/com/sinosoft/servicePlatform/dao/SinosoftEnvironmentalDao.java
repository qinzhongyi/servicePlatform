package com.sinosoft.servicePlatform.dao;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;

/**
 * 
 * 文件名字： SinosoftEnvironmentalDao
 * @author 作者：qinzy
 * 功能描述：1.
 * 创建时间: 2018年7月6日 下午4:49:51
 * 所属公司: sinosoft
 */
public interface SinosoftEnvironmentalDao {
	
	public void save(SinosoftEnvironmental se);
	
	public void delete(SinosoftEnvironmental se);
	
	public SinosoftEnvironmental queryById(String eId);
	
	public List<SinosoftEnvironmental> queryAll(SinosoftEnvironmental se);
	
	public void update(SinosoftEnvironmental se);

}
