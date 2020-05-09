package com.sinosoft.servicePlatform.dao;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;

public interface SinosoftUserDao {
	
	public void merge(Sinosoft_User su);
	
	public void delete(Sinosoft_User su);
	
	public Sinosoft_User findByUserId(String uId);
	
	public List<Sinosoft_User> findAll(Sinosoft_User su);
	
	public void update(Sinosoft_User su);
}
