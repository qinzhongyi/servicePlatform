package com.sinosoft.servicePlatform.dao;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftUserInfo;

public interface SinosoftUserInfoDao {
	
	public void save(SinosoftUserInfo su);
	
	public void delete(SinosoftUserInfo su);
	
	public SinosoftUserInfo queryById(String uId);
	
	public List<SinosoftUserInfo> queryAll(SinosoftUserInfo su);
	
	public void update(SinosoftUserInfo su);
}
