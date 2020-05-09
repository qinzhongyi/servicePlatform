package com.sinosoft.servicePlatform.service;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;
import com.sinosoft.servicePlatform.pojo.vo.UserReqVo;

public interface UserService {
	
	public void del(UserReqVo vo) throws Exception;
	
	public Sinosoft_User query(UserReqVo vo) throws Exception;
	
	public void update(UserReqVo vo) throws Exception;

	public List<Sinosoft_User> sel(UserReqVo vo) throws Exception;

	public List<Sinosoft_User> login(UserReqVo vo) throws Exception;

	public void register(UserReqVo vo) throws Exception;
	
	public String getToken();

	public void logout(UserReqVo vo) throws Exception;

}
