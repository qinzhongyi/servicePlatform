package com.sinosoft.servicePlatform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.servicePlatform.config.CreateIDConfig;
import com.sinosoft.servicePlatform.dao.SinosoftEnvironmentalDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;
import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;
import com.sinosoft.servicePlatform.pojo.vo.EnvironmentalReqVo;
import com.sinosoft.servicePlatform.service.EnvironmentalService;
import com.sinosoft.servicePlatform.util.CheckDataUtil;
import com.sinosoft.servicePlatform.util.OnlineUserCountUtil;

@Service
public class EnvironmentalServiceImpl implements EnvironmentalService{

	@Autowired
	SinosoftEnvironmentalDao seDao;
	@Autowired
	CheckDataUtil checkDataUtil;
	@Autowired
	OnlineUserCountUtil onlineUserCountUtil;

	@Override
	public void del(EnvironmentalReqVo vo) throws Exception {
		SinosoftEnvironmental sedto = vo.getEnv();
		Object[] o = {sedto};
		Object[] oName = {"环境配置信息"};
		checkDataUtil.checkIsNull(o, oName);
		o = new Object[]{sedto.geteId()};
		oName = new Object[]{"环境编码"};
		checkDataUtil.checkIsNull(o, oName);
		sedto = seDao.queryById(sedto.geteId());
		seDao.delete(sedto);
	}

	@Override
	public SinosoftEnvironmental query(EnvironmentalReqVo vo) {
		SinosoftEnvironmental sedto = vo.getEnv();
		return seDao.queryById(sedto.geteId());
	}

	@Override
	public List<SinosoftEnvironmental> update(EnvironmentalReqVo vo) throws Exception {
		SinosoftEnvironmental sedto = vo.getEnv();
		Object[] o = {sedto};
		Object[] oName = {"环境配置信息"};
		checkDataUtil.checkIsNull(o, oName);
		o = new Object[]{sedto.geteName(),sedto.getValied()};
		oName = new Object[]{"环境名称","是否有效"};
		checkDataUtil.checkIsNull(o, oName);
		Sinosoft_User su = onlineUserCountUtil.init().getUser(vo.getHead().getToken());
		sedto.setcUserCode(su.getUserCode());
		sedto.setcUserName(su.getUserName());
		if(sedto.geteId() != null && !"".equals(sedto.geteId())){
			sedto.setUpdateDate(new Date());
		}else{
			sedto.seteId(CreateIDConfig.createEnvId());
		}
		seDao.save(sedto);
		List<SinosoftEnvironmental> sedtoList = new ArrayList<SinosoftEnvironmental>();
		sedtoList.add(sedto);
		return sedtoList;
	}

	@Override
	public List<SinosoftEnvironmental> sel(EnvironmentalReqVo vo) {
		SinosoftEnvironmental sedto = vo.getEnv();
		return seDao.queryAll(sedto);
	}

}
