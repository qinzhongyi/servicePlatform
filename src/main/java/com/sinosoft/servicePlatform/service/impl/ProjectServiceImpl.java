package com.sinosoft.servicePlatform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.servicePlatform.config.CreateIDConfig;
import com.sinosoft.servicePlatform.dao.SinosoftProjectDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftProject;
import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;
import com.sinosoft.servicePlatform.pojo.vo.ProjectReqVo;
import com.sinosoft.servicePlatform.service.ProjectService;
import com.sinosoft.servicePlatform.util.CheckDataUtil;
import com.sinosoft.servicePlatform.util.OnlineUserCountUtil;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	SinosoftProjectDao seDao;
	@Autowired
	CheckDataUtil checkDataUtil;
	@Autowired
	OnlineUserCountUtil onlineUserCountUtil;

	@Override
	public void del(ProjectReqVo vo) throws Exception {
		SinosoftProject sedto = vo.getProject();
		Object[] o = {sedto};
		Object[] oName = {"项目信息"};
		checkDataUtil.checkIsNull(o, oName);
		o = new Object[]{sedto.getPId()};
		oName = new Object[]{"项目识别码"};
		checkDataUtil.checkIsNull(o, oName);
		sedto = seDao.queryById(sedto.getPId());
		seDao.delete(sedto);
	}

	@Override
	public SinosoftProject query(ProjectReqVo vo) {
		SinosoftProject sedto = vo.getProject();
		return seDao.queryById(sedto.getPId());
	}

	@Override
	public void update(ProjectReqVo vo) throws Exception {
		SinosoftProject sedto = vo.getProject();
		Object[] o = {sedto};
		Object[] oName = {"项目信息"};
		checkDataUtil.checkIsNull(o, oName);
		o = new Object[]{sedto.getPName(), sedto.getPAddr(), sedto.getValied(), sedto.getEId()};
		oName = new Object[]{"项目名称", "项目地址", "有效标志", "环境编码"};
		checkDataUtil.checkIsNull(o, oName);
		Sinosoft_User su = onlineUserCountUtil.init().getUser(vo.getHead().getToken());
		sedto.setCUserCode(su.getUserCode());
		sedto.setCUserName(su.getUserName());
		if(sedto.getPId() != null && !"".equals(sedto.getPId())){
			sedto.setUpdateDate(new Date());
		}else{
			sedto.setPId(CreateIDConfig.createProjectId());
		}
		seDao.save(sedto);
	}

	@Override
	public List<SinosoftProject> sel(ProjectReqVo vo) {
		SinosoftProject sedto = vo.getProject();
		return seDao.queryAll(sedto);
	}

}
