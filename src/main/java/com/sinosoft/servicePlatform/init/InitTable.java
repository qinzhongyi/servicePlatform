package com.sinosoft.servicePlatform.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.sinosoft.servicePlatform.dao.InitMysqlTableDao;
import com.sinosoft.servicePlatform.dao.impl.Work;

/**
 * 表结构初始化 文件名字： InitTable
 * 
 * @author 作者：qinzy 功能描述：1. 创建时间: 2018年6月26日 下午5:33:30 所属公司: sinosoft
 */
@Component
@Order(value=2)
public class InitTable implements CommandLineRunner{

	private static final Logger logger = LoggerFactory.getLogger(Work.class);
	
	@Value("${initSqlPath}")
	private String url = "";
	
	@Autowired
	private InitMysqlTableDao initMysqlTableDao;

	/**
	 * 流程如下： 1.判断是否存在表 2.如果不存在则新建表 3.如果存在则检查是否存在新表 4.如果存在新表则创建新表
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */
	public void getTableSql() throws FileNotFoundException {
		BufferedReader reader = null;
		String sqlText = "";
		logger.info("加载表数据地址：" + url);
		List<String> sqlList = new ArrayList<String>();
		File configFile = new File(url);
		try {
			// 以行为单位读取文件内容，一次读一整行
			reader = new BufferedReader(new FileReader(configFile));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (tempString != null && !"".equals(tempString)) {
					// 忽略注释
					if ("#".equals(tempString.substring(0, 1))) {
						continue;
					}
					if (tempString.length() > 2 && "/*".equals(tempString.substring(0, 2))) {
						continue;
					}
					if (";".equals(tempString.substring(tempString.length() - 1, tempString.length()))) {
						sqlText += tempString;
						String s = new String(sqlText);
						sqlList.add(s);
						sqlText = "";
					} else {
						sqlText += tempString;
					}
				}
			}
			reader.close();
			initMysqlTableDao.getDbType(sqlList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run(String... arg0) throws Exception {
		getTableSql();
	}

}
