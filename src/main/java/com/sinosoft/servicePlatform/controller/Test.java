package com.sinosoft.servicePlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.servicePlatform.dao.SinosoftEnvironmentalDao;
import com.sinosoft.servicePlatform.pojo.vo.ResHeadVo;

@RestController
public class Test {

	@Autowired
	SinosoftEnvironmentalDao seDao;
	
	@RequestMapping(value="/test",method = { RequestMethod.POST })
	public ResHeadVo test(@RequestBody Object vo){
//		seDao.save(se);
		ResHeadVo rVo = new ResHeadVo();
		rVo.seteCode("#0000");
		rVo.setMsg("成功");
		return rVo;
	}
}
