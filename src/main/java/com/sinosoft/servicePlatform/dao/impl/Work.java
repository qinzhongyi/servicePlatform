package com.sinosoft.servicePlatform.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.ReturningWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

@Service
@Transactional
public class Work implements ReturningWork<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(Work.class);

	private List<String> sqlList;

	@Override
	public Object execute(Connection conn) throws SQLException {
		try {
			for (String s : sqlList) {
				try {
					conn.prepareStatement(s).execute();
					if(s.length() > 13 && "create table ".equals(s.substring(0,13).toLowerCase())){
						logger.info(s.substring(13,s.indexOf("(")) + "表已创建！");
					}
					if(s.length() > 12 && "INSERT INTO ".equals(s.substring(0,12).toUpperCase())){
						logger.info(s.substring(13,s.indexOf("(")) + "数据已初始化！");
					}
				} catch (MySQLSyntaxErrorException e) {
					String str = e.getMessage();
					logger.info(str.substring(7,str.lastIndexOf("'")) + "表已存在！");
				} catch (MySQLIntegrityConstraintViolationException e) {
					logger.info("数据库初始化数据已存在！");
				} catch (Exception e) {
					if("Field 'user_id' doesn't have a default value".equals(e.getMessage())){
						logger.info("数据库初始化数据已存在！");
					}else{
						e.printStackTrace();
						logger.error(s.substring(13,s.indexOf("(")) + "表创建异常！" + e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化表异常！" + e.getMessage());
		}
		return null;
	}

	public List<String> getSqlList() {
		return sqlList;
	}

	public void setSqlList(List<String> sqlList) {
		this.sqlList = sqlList;
	}

}
