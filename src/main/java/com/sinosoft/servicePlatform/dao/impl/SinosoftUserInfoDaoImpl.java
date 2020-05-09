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

import com.sinosoft.servicePlatform.dao.SinosoftUserInfoDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftUserInfo;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;

@Service
@Transactional
public class SinosoftUserInfoDaoImpl implements SinosoftUserInfoDao {

	private static final Logger logger = LoggerFactory.getLogger(SinosoftUserInfoDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@Override
	public void save(SinosoftUserInfo su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(su);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0301";
			errorCodeUtil.msg = "SinosoftUserInfo表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void delete(SinosoftUserInfo su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(session.merge(su));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0302";
			errorCodeUtil.msg = "SinosoftUserInfo表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void update(SinosoftUserInfo su) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(su);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "SinosoftUserInfo表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public SinosoftUserInfo queryById(String uId) {
		SinosoftUserInfo s = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from SinosoftUserInfoDto s where s.UserID = ?");
			query.setParameter(0, uId);
			List<SinosoftUserInfo> sList = query.list();
			if (sList != null && sList.size() > 0) {
				s = sList.get(0);
			}
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftUserInfo表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SinosoftUserInfo> queryAll(SinosoftUserInfo su) {
		List<SinosoftUserInfo> sList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("from SinosoftUserInfoDto s where 1 = 1 ");
			int i = 0;
			if (su != null) {
				if (su.getUserID() != null && !"".equals(su.getUserID())) {
					sql.append("and s.UserID = ?");
				}
				if (su.getMobile() != null && !"".equals(su.getMobile())) {
					sql.append("and s.Mobile = ?");
				}
				if (su.getEMail() != null && !"".equals(su.getEMail())) {
					sql.append("and s.EMail = ?");
				}
				if (su.getWeChar() != null && !"".equals(su.getWeChar())) {
					sql.append("and s.WeChar = ?");
				}
			}
			Query query = session.createQuery(sql.toString());
			if (su != null) {
				if (su.getUserID() != null && !"".equals(su.getUserID())) {
					query.setString(i++, su.getUserID());
				}
				if (su.getMobile() != null && !"".equals(su.getMobile())) {
					query.setString(i++, su.getMobile());
				}
				if (su.getEMail() != null && !"".equals(su.getEMail())) {
					query.setString(i++, su.getEMail());
				}
				if (su.getWeChar() != null && !"".equals(su.getWeChar())) {
					query.setString(i++, su.getWeChar());
				}
			}
			sList = query.list();
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftUserInfo表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return sList;
	}

}
