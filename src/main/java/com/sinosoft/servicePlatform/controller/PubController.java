package com.sinosoft.servicePlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.servicePlatform.config.CreateIDConfig;
import com.sinosoft.servicePlatform.config.TransCodeBaseConfig;
import com.sinosoft.servicePlatform.pojo.vo.EnvironmentalReqVo;
import com.sinosoft.servicePlatform.pojo.vo.EnvironmentalResVo;
import com.sinosoft.servicePlatform.pojo.vo.ProjectReqVo;
import com.sinosoft.servicePlatform.pojo.vo.ProjectResVo;
import com.sinosoft.servicePlatform.pojo.vo.ReqHeadVo;
import com.sinosoft.servicePlatform.pojo.vo.ResHeadVo;
import com.sinosoft.servicePlatform.pojo.vo.UserReqVo;
import com.sinosoft.servicePlatform.pojo.vo.UserResVo;
import com.sinosoft.servicePlatform.service.EnvironmentalService;
import com.sinosoft.servicePlatform.service.ProjectService;
import com.sinosoft.servicePlatform.service.UserService;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;
import com.sinosoft.servicePlatform.util.OnlineUserCountUtil;

/**
 * 公共请求控制类 文件名字： PubService
 * 
 * @author 作者：qinzy 功能描述：1. 创建时间: 2018年7月9日 下午5:43:35 所属公司: sinosoft
 */
@RestController
public class PubController {

	@Autowired
	private EnvironmentalService eService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private OnlineUserCountUtil onlineUserCountUtil;

	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@RequestMapping(value = "/environmental", method = { RequestMethod.POST })
	public EnvironmentalResVo environmental(@RequestBody EnvironmentalReqVo vo) {
		errorCodeUtil = new ErrorCodeUtil();
		ReqHeadVo head = vo.getHead();
		ResHeadVo rVo = new ResHeadVo();
		EnvironmentalResVo resVo = new EnvironmentalResVo();
		String transCode = head.getTransCode().toUpperCase();
		if (!CreateIDConfig.checkToken(head.getToken())) {
			errorCodeUtil.eCode = "#1004";
			errorCodeUtil.msg = "未授权接口！";
		} else if (!onlineUserCountUtil.init().checkUser(head.getToken())) {
			errorCodeUtil.eCode = "#1005";
			errorCodeUtil.msg = "token信息已失效！";
		} else {
			try {
				if (TransCodeBaseConfig.UPDATE.equals(transCode)) {
					resVo.setEnv(eService.update(vo));
				} else if (TransCodeBaseConfig.DEL.equals(transCode)) {
					eService.del(vo);
				} else if (TransCodeBaseConfig.SEL.equals(transCode)) {
					resVo.setEnv(eService.sel(vo));
				} else {
					errorCodeUtil.eCode = "#1003";
					errorCodeUtil.msg = "environmental接口交互类型不正确！";
				}
			} catch (Exception e) {
				if ("#0000".equals(errorCodeUtil.eCode)) {
					errorCodeUtil.eCode = "#1111";// 非传统异常
					errorCodeUtil.msg = "environmental接口出现异常，异常原因：" + e.getMessage();
				}
			}
		}
		rVo.seteCode(errorCodeUtil.eCode);
		rVo.setMsg(errorCodeUtil.msg);
		resVo.setHead(rVo);
		return resVo;
	}

	@RequestMapping(value = "/user", method = { RequestMethod.POST })
	public UserResVo user(@RequestBody UserReqVo vo) {
		errorCodeUtil = new ErrorCodeUtil();
		ReqHeadVo head = vo.getHead();
		ResHeadVo rVo = new ResHeadVo();
		UserResVo userResVo = new UserResVo();
		String transCode = head.getTransCode().toUpperCase();
		if (!CreateIDConfig.checkToken(head.getToken())) {
			errorCodeUtil.eCode = "#1004";
			errorCodeUtil.msg = "未授权接口！";
		} else if (!onlineUserCountUtil.init().checkUser(head.getToken())) {
			errorCodeUtil.eCode = "#1005";
			errorCodeUtil.msg = "token信息已失效！";
		} else {
			try {
				if (TransCodeBaseConfig.UPDATE.equals(transCode)) {
					userService.update(vo);
				} else if (TransCodeBaseConfig.DEL.equals(transCode)) {
					userService.del(vo);
				} else if (TransCodeBaseConfig.SEL.equals(transCode)) {
					userResVo.setUserList(userService.sel(vo));
				} else {
					errorCodeUtil.eCode = "#1003";
					errorCodeUtil.msg = "user接口交互类型不正确！";
				}
			} catch (Exception e) {
				if ("#0000".equals(errorCodeUtil.eCode)) {
					errorCodeUtil.eCode = "#1111";// 非传统异常
					errorCodeUtil.msg = "user接口出现异常，异常原因：" + e.getMessage();
				}
			}
		}
		rVo.seteCode(errorCodeUtil.eCode);
		rVo.setMsg(errorCodeUtil.msg);
		userResVo.setHead(rVo);
		return userResVo;
	}

	@RequestMapping(value = "/project", method = { RequestMethod.POST })
	public ProjectResVo project(@RequestBody ProjectReqVo vo) {
		errorCodeUtil = new ErrorCodeUtil();
		ReqHeadVo head = vo.getHead();
		ResHeadVo rVo = new ResHeadVo();
		ProjectResVo projectResVo = new ProjectResVo();
		String transCode = head.getTransCode().toUpperCase();
		if (!CreateIDConfig.checkToken(head.getToken())) {
			errorCodeUtil.eCode = "#1004";
			errorCodeUtil.msg = "未授权接口！";
		} else if (!onlineUserCountUtil.init().checkUser(head.getToken())) {
			errorCodeUtil.eCode = "#1005";
			errorCodeUtil.msg = "token信息已失效！";
		} else {
			try {
				if (TransCodeBaseConfig.UPDATE.equals(transCode)) {
					projectService.update(vo);
				} else if (TransCodeBaseConfig.DEL.equals(transCode)) {
					projectService.del(vo);
				} else if (TransCodeBaseConfig.SEL.equals(transCode)) {
					projectResVo.setProject(projectService.sel(vo));
				} else {
					errorCodeUtil.eCode = "#1003";
					errorCodeUtil.msg = "project接口交互类型不正确！";
				}
			} catch (Exception e) {
				if ("#0000".equals(errorCodeUtil.eCode)) {
					errorCodeUtil.eCode = "#1111";// 非传统异常
					errorCodeUtil.msg = "project接口出现异常，异常原因：" + e.getMessage();
				}
			}
		}
		rVo.seteCode(errorCodeUtil.eCode);
		rVo.setMsg(errorCodeUtil.msg);
		projectResVo.setHead(rVo);
		return projectResVo;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public UserResVo login(@RequestBody UserReqVo vo) {
		errorCodeUtil = new ErrorCodeUtil();
		ReqHeadVo head = vo.getHead();
		ResHeadVo rVo = new ResHeadVo();
		String transCode = head.getTransCode().toUpperCase();
		UserResVo userResVo = new UserResVo();
		try {
			if (TransCodeBaseConfig.LOGIN.equals(transCode)) {
				userResVo.setUserList(userService.login(vo));
				userResVo.setToken(userService.getToken());
			} else if (TransCodeBaseConfig.REGISTER.equals(transCode)) {
				userService.register(vo);
			} else if (TransCodeBaseConfig.LOGOUT.equals(transCode)) {
				userService.logout(vo);
			} else {
				errorCodeUtil.eCode = "#1003";
				errorCodeUtil.msg = "login接口交互类型不正确！";
			}
		} catch (Exception e) {
			if ("#0000".equals(errorCodeUtil.eCode)) {
				errorCodeUtil.eCode = "#1111";// 非传统异常
				errorCodeUtil.msg = "login接口出现异常，异常原因：" + e.getMessage();
			}
		}
		rVo.seteCode(errorCodeUtil.eCode);
		rVo.setMsg(errorCodeUtil.msg);
		userResVo.setHead(rVo);
		return userResVo;
	}
}
