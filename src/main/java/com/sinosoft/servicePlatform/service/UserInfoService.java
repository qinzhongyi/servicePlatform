package com.sinosoft.servicePlatform.service;

import java.util.List;

import com.sinosoft.servicePlatform.pojo.dto.SinosoftUserInfo;
import com.sinosoft.servicePlatform.pojo.vo.UserInfoReqVo;

public interface UserInfoService {
	
	public void del(UserInfoReqVo vo) throws Exception;
	
	public SinosoftUserInfo query(UserInfoReqVo vo);
	
	public void update(UserInfoReqVo vo) throws Exception;

	public List<SinosoftUserInfo> sel(UserInfoReqVo vo);

}
