package com.sinosoft.servicePlatform.dao;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftSharedBuffer;

public interface SinosoftSharedBufferDao {
	
	public void merge(SinosoftSharedBuffer su);
	
	public void delete(SinosoftSharedBuffer su);
	
	public SinosoftSharedBuffer findByToken(String token);
	
	public List<SinosoftSharedBuffer> findAll(SinosoftSharedBuffer su);
	
	public void update(SinosoftSharedBuffer su);

	public void removelangDateUser();
}
