package com.sinosoft.servicePlatform.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.servicePlatform.dao.SinosoftSharedBufferDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftSharedBuffer;
import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;

@Component
public class OnlineUserCountUtil {

	@Autowired
	SinosoftSharedBufferDao sinosoftSharedBufferDao;
	
	// 用来记录当前已经登录的用户信息(用Collections控制同步)
	private Map<String, Sinosoft_User> userMap = Collections.synchronizedMap(new HashMap<String, Sinosoft_User>());

	private static OnlineUserCountUtil onlineUserCount;
	@Autowired
	private OnlineUserCountUtil ouc;
	
	@PostConstruct
	public OnlineUserCountUtil init(){
		onlineUserCount = ouc;
		return onlineUserCount;
	}

	// 获取所有
	public Collection<Sinosoft_User> getAllUsers() {
		return this.userMap.values();
	}

	// 获取单个用户
	public Sinosoft_User getUser(String token) {
		return this.userMap.get(token);
	}

	// 校验用户信息
	public boolean checkUser(String token) {
		boolean bool = false;
		if (checkUserOnline(token)) {
			bool = true;
		}
		return bool;
	}

	// 增加
	public void addUser(String token, Sinosoft_User user, String ip) {
		SinosoftSharedBuffer sinosoftSharedBuffer = new SinosoftSharedBuffer();
		sinosoftSharedBuffer.setIp(ip);
		sinosoftSharedBuffer.setToken(token);
		sinosoftSharedBuffer.setUserCode(user.getUserCode());
		sinosoftSharedBuffer.setValidated("1");
		sinosoftSharedBufferDao.merge(sinosoftSharedBuffer);
		this.userMap.put(token, user);
	}

	// 移除
	public void removeUser(String token) {
		SinosoftSharedBuffer sinosoftSharedBuffer = sinosoftSharedBufferDao.findByToken(token);
		if (sinosoftSharedBuffer != null) {
			sinosoftSharedBuffer.setValidated("0");
			sinosoftSharedBufferDao.update(sinosoftSharedBuffer);
		}
		this.userMap.remove(token);
	}

	public List<String> getSessionIdList() {
		List<String> sessionIdList = new ArrayList<String>();
		for (String sessionId : this.userMap.keySet()) {
			sessionIdList.add(sessionId);
		}
		return sessionIdList;
	}

	public boolean checkUserOnline(String token) {
		boolean bool = true;
		sinosoftSharedBufferDao.removelangDateUser();
		SinosoftSharedBuffer sinosoftSharedBuffer = sinosoftSharedBufferDao.findByToken(token);
		if (sinosoftSharedBuffer != null) {
			sinosoftSharedBuffer.setUpdateDate(new Date());
			sinosoftSharedBufferDao.update(sinosoftSharedBuffer);
		} else {
			bool = false;
		}
		return bool;
	}
}
