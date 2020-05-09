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

import com.sinosoft.servicePlatform.dao.SinosoftProjectDao;
import com.sinosoft.servicePlatform.pojo.dto.SinosoftProject;
import com.sinosoft.servicePlatform.util.ErrorCodeUtil;

@Service
@Transactional
public class SinosoftProjectDaoImpl implements SinosoftProjectDao {

	private static final Logger logger = LoggerFactory.getLogger(SinosoftProjectDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	ErrorCodeUtil errorCodeUtil;

	@Override
	public void save(SinosoftProject sp) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.merge(sp);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0301";
			errorCodeUtil.msg = "SinosoftProject表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void delete(SinosoftProject sp) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(session.merge(sp));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0302";
			errorCodeUtil.msg = "SinosoftProject表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	public void update(SinosoftProject sp) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(sp);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			errorCodeUtil.eCode = "#0304";
			errorCodeUtil.msg = "SinosoftProject表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public SinosoftProject queryById(String pId) {
		SinosoftProject s = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from SinosoftProject s where s.PId = ?");
			query.setParameter(0, pId);
			List<SinosoftProject> sList = query.list();
			if (sList != null && sList.size() > 0) {
				s = sList.get(0);
			}
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftProject表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SinosoftProject> queryAll(SinosoftProject sp) {
		List<SinosoftProject> sList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("from SinosoftProject s where 1 = 1 ");
			int i = 0;
			if (sp != null) {
				if (sp.getCUserCode() != null && !"".equals(sp.getCUserCode())) {
					sql.append("and s.CUserCode = ?");
				}
				if (sp.getPId() != null && !"".equals(sp.getPId())) {
					sql.append("and s.PId = ?");
				}
				if (sp.getValied() != null && !"".equals(sp.getValied())) {
					sql.append("and s.Valied = ?");
				}
				if (sp.getValied() != null && !"".equals(sp.getValied())) {
					sql.append("and s.EId = ?");
				}
			}
			Query query = session.createQuery(sql.toString());
			if (sp != null) {
				if (sp.getCUserCode() != null && !"".equals(sp.getCUserCode())) {
					query.setString(i++, sp.getCUserCode());
				}
				if (sp.getPId() != null && !"".equals(sp.getPId())) {
					query.setString(i++, sp.getPId());
				}
				if (sp.getValied() != null && !"".equals(sp.getValied())) {
					query.setString(i++, sp.getValied());
				}
				if (sp.getEId() != null && !"".equals(sp.getEId())) {
					query.setString(i++, sp.getEId());
				}
			}
			sList = query.list();
		} catch (Exception e) {
			errorCodeUtil.eCode = "#0303";
			errorCodeUtil.msg = "SinosoftProject表操作异常：" + e.getMessage();
			logger.error(errorCodeUtil.msg);
			throw e;
		}
		return sList;
	}

}
