package com.sinosoft.servicePlatform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.servicePlatform.config.CreateIDConfig;
import com.sinosoft.servicePlatform.dao.SinosoftUserDao;
import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;
import com.sinosoft.servicePlatform.pojo.vo.UserReqVo;
import com.sinosoft.servicePlatform.service.UserService;
import com.sinosoft.servicePlatform.util.CheckDataUtil;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;
import com.sinosoft.servicePlatform.util.OnlineUserCountUtil;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	SinosoftUserDao seDao;
	@Autowired
	CheckDataUtil checkDataUtil;
	@Autowired
	ErrorCodeUtil errorCodeUtil;
	@Autowired
	OnlineUserCountUtil onlineUserCountUtil;
	
	String token;

	@Override
	public void del(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		Object[] o = new Object[]{sedto.getUserId()};
		Object[] oName = new Object[]{"用户唯一识别码"};
		checkDataUtil.checkIsNull(o, oName);
		sedto = seDao.findByUserId(sedto.getUserId());
		seDao.delete(sedto);
	}

	@Override
	public Sinosoft_User query(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		return seDao.findByUserId(sedto.getUserId());
	}

	@Override
	public void update(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		Object[] o = new Object[]{sedto.getUserCode(), sedto.getUserName()};
		Object[] oName = new Object[]{"用户编码", "用户名称"};
		checkDataUtil.checkIsNull(o, oName);
		if(sedto.getUserId() == null || "".equals(sedto.getUserId())){
			sedto.setUserId(CreateIDConfig.createUserId());
			seDao.merge(sedto);
		}else{
			Sinosoft_User sedtoOther = seDao.findByUserId(sedto.getUserId());
			if(sedtoOther != null){
				if(sedto.getUserPwd() == null || "".equals(sedto.getUserPwd())){//这个是修改用户信息，不改密码
					sedto.setUserPwd(sedtoOther.getUserPwd());
					sedto.setUpdateDate(new Date());
				}
				seDao.update(sedto);
			}else{
				sedto.setUserId(CreateIDConfig.createUserId());
				seDao.merge(sedto);
			}
		}
	}

	@Override
	public List<Sinosoft_User> sel(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		return seDao.findAll(sedto);
	}

	@Override
	public List<Sinosoft_User> login(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		Object[] o = new Object[]{sedto.getUserCode(), sedto.getUserPwd()};
		Object[] oName = new Object[]{"用户编码", "用户密码"};
		checkDataUtil.checkIsNull(o, oName);
		List<Sinosoft_User> userList = seDao.findAll(sedto);
		if(userList == null || userList.size() == 0){
			errorCodeUtil.eCode = "#0201";
			errorCodeUtil.msg = "用户名或者密码不正确";
			throw new Exception(errorCodeUtil.msg);
		}
		userList.get(0).setUserPwd(null);
		userList.get(0).setCreateDate(null);
		userList.get(0).setUpdateDate(null);
		token = CreateIDConfig.createToken();
		onlineUserCountUtil.init().addUser(token, userList.get(0), vo.getIp());
		return userList;
	}

	@Override
	public void register(UserReqVo vo) throws Exception{
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		Object[] o = new Object[]{sedto.getUserCode(), sedto.getUserPwd()};
		Object[] oName = new Object[]{"用户编码", "用户密码"};
		checkDataUtil.checkIsNull(o, oName);
		String pwd = sedto.getUserPwd();
		sedto.setUserPwd("");
		List<Sinosoft_User> userList = seDao.findAll(sedto);
		if(userList != null && userList.size() > 0){
			errorCodeUtil.eCode = "#0202";
			errorCodeUtil.msg = "用户名已存在";
			throw new Exception(errorCodeUtil.msg);
		}else{
			sedto.setUserPwd(pwd);
			update(vo);
		}
	}

	@Override
	public void logout(UserReqVo vo) throws Exception {
		Sinosoft_User sedto = vo.getUser();
		checkObject(sedto);
		Object[] o = new Object[]{sedto.getUserCode()};
		Object[] oName = new Object[]{"用户编码"};
		checkDataUtil.checkIsNull(o, oName);
		List<Sinosoft_User> userList = seDao.findAll(sedto);
		if(userList != null && userList.size() > 0){
			onlineUserCountUtil.init().removeUser(vo.getHead().getToken());
		}
	}
	
	public void checkObject(Object o) throws Exception{
		Object[] os = {o};
		Object[] oNames = {"用户信息"};
		checkDataUtil.checkIsNull(os, oNames);
	}

	@Override
	public String getToken() {
		return this.token;
	}

}
