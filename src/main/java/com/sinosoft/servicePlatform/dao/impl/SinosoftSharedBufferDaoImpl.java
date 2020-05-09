package com.sinosoft.servicePlatform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.servicePlatform.dao.SinosoftSharedBufferDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftSharedBuffer;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;

@Service
@Transactional
public class SinosoftSharedBufferDaoImpl implements SinosoftSharedBufferDao{

	private static final Logger logger = LoggerFactory.getLogger(SinosoftSharedBufferDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@Override
	public void merge(SinosoftSharedBuffer ss) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(ss);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0301";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void delete(SinosoftSharedBuffer ss) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(session.merge(ss));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0302";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void update(SinosoftSharedBuffer ss) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(ss);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public SinosoftSharedBuffer findByToken(String token) {
		SinosoftSharedBuffer s = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from SinosoftSharedBuffer s where s.token = ? and s.validated = ? ");
			query.setString(0, token);
			query.setString(1, "1");
			List<SinosoftSharedBuffer> sList = query.list();
			if (sList != null && sList.size() > 0) {
				s = sList.get(0);
			}
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SinosoftSharedBuffer> findAll(SinosoftSharedBuffer ss) {
		List<SinosoftSharedBuffer> sList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("from SinosoftSharedBuffer s where s.validated = ? ");
			Query query = session.createQuery(sql.toString());
			query.setString(0, "1");
			sList = query.list();
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return sList;
	}

	@Override
	public void removelangDateUser() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "update SinosoftSharedBuffer s set s.validated = ? where TIMESTAMPDIFF(MINUTE, s.updateDate, now()) > 20 and s.validated = ?";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString(0, "0");
			sqlQuery.setString(1, "1");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "SinosoftSharedBuffer表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}


}
