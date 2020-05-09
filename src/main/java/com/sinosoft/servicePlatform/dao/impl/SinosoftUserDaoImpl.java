package com.sinosoft.servicePlatform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.servicePlatform.dao.SinosoftUserDao;
import com.sinosoft.servicePlatform.pojo.dto.Sinosoft_User;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;

@Service
@Transactional
public class SinosoftUserDaoImpl implements SinosoftUserDao{

	private static final Logger logger = LoggerFactory.getLogger(SinosoftUserDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@Override
	public void merge(Sinosoft_User su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(su);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0301";
			errorCodeUtil.msg = "Sinosoft_User表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void delete(Sinosoft_User su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(session.merge(su));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0302";
			errorCodeUtil.msg = "Sinosoft_User表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void update(Sinosoft_User su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(su);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "Sinosoft_User表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Sinosoft_User findByUserId(String uId) {
		Sinosoft_User s = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createSQLQuery("from Sinosoft_User s where s.UserId = ?");
			query.setString(0, uId);
			List<Sinosoft_User> sList = query.list();
			if (sList != null && sList.size() > 0) {
				s = sList.get(0);
			}
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "Sinosoft_User表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Sinosoft_User> findAll(Sinosoft_User su) {
		List<Sinosoft_User> sList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("from Sinosoft_User s where 1 = 1 ");
			int i = 0;
			if (su != null) {
				if (su.getUserCode() != null && !"".equals(su.getUserCode())) {
					sql.append(" and s.UserCode=?");
				}
				if (su.getUserId() != null && !"".equals(su.getUserId())) {
					sql.append(" and s.UserId=?");
				}
				if (su.getUserPwd() != null && !"".equals(su.getUserPwd())) {
					sql.append(" and s.UserPwd=?");
				}
				if (su.getUserName() != null && !"".equals(su.getUserName())) {
					sql.append(" and s.UserName=?");
				}
			}
			Query query = session.createQuery(sql.toString());
			if (su != null) {
				if (su.getUserCode() != null && !"".equals(su.getUserCode())) {
					query.setParameter(i++, su.getUserCode());
				}
				if (su.getUserId() != null && !"".equals(su.getUserId())) {
					query.setParameter(i++, su.getUserId());
				}
				if (su.getUserPwd() != null && !"".equals(su.getUserPwd())) {
					query.setParameter(i++, su.getUserPwd());
				}
				if (su.getUserName() != null && !"".equals(su.getUserName())) {
					query.setParameter(i++, su.getUserName());
				}
			}
			sList = query.list();
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "Sinosoft_User表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return sList;
	}


}
