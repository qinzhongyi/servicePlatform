package com.sinosoft.servicePlatform.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.servicePlatform.dao.SinosoftEnvironmentalDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftEnvironmental;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;

@Service
@Transactional
public class SinosoftEnvironmentalDaoImpl implements SinosoftEnvironmentalDao {

	private static final Logger logger = LoggerFactory.getLogger(SinosoftEnvironmentalDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@Override
	public void save(SinosoftEnvironmental se) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(se);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0301";
			errorCodeUtil.msg = "SinosoftEnvironmental表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void delete(SinosoftEnvironmental se) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(session.merge(se));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0302";
			errorCodeUtil.msg = "SinosoftEnvironmental表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void update(SinosoftEnvironmental se) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(se);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "SinosoftEnvironmental表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public SinosoftEnvironmental queryById(String eId) {
		SinosoftEnvironmental s = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from SinosoftEnvironmental s where s.eId = ?");
			query.setParameter(0, eId);
			List<SinosoftEnvironmental> sList = query.list();
			if (sList != null && sList.size() > 0) {
				s = sList.get(0);
			}
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftEnvironmental表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SinosoftEnvironmental> queryAll(SinosoftEnvironmental se) {
		List<SinosoftEnvironmental> sList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("from SinosoftEnvironmental s where 1 = 1 ");
			int i = 0;
			if (se != null) {
				if (se.getcUserCode() != null && !"".equals(se.getcUserCode())) {
					sql.append("and s.cUserCode = ?");
				}
				if (se.geteId() != null && !"".equals(se.geteId())) {
					sql.append("and s.eId = ?");
				}
				if (se.getValied() != null && !"".equals(se.getValied())) {
					sql.append("and s.valied = ?");
				}
			}
			Query query = session.createQuery(sql.toString());
			if (se != null) {
				if (se.getcUserCode() != null && !"".equals(se.getcUserCode())) {
					query.setString(i++, se.getcUserCode());
				}
				if (se.geteId() != null && !"".equals(se.geteId())) {
					query.setString(i++, se.geteId());
				}
				if (se.getValied() != null && !"".equals(se.getValied())) {
					query.setString(i++, se.getValied());
				}
			}
			sList = query.list();
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftEnvironmental表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return sList;
	}

}
