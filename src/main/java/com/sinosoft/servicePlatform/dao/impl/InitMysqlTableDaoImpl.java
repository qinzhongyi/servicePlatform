package com.sinosoft.servicePlatform.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.servicePlatform.dao.InitMysqlTableDao;

/**
 * 
 * 文件名字： DapImplInitMysqlTable
 * 
 * @author 作者：qinzy 功能描述：1. 创建时间: 2018年6月27日 下午5:34:19 所属公司: sinosoft
 */
@Service
@Transactional
public class InitMysqlTableDaoImpl implements InitMysqlTableDao{

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private Work work;
	
	public void getDbType(List<String> sqlList) {
		work.setSqlList(sqlList);
		sessionFactory.getCurrentSession().doReturningWork(work);
	}

}
