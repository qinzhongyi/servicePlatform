package com.sinosoft.servicePlatform.dao;

import java.util.List;

/**
 * 初始化数据库配置底层接口
 * 文件名字： DaoInitMysqlTable
 * @author 作者：qinzy
 * 功能描述：1.
 * 创建时间: 2018年6月27日 下午5:33:34
 * 所属公司: sinosoft
 */
public interface InitMysqlTableDao {
	
	public void getDbType(List<String> sqlList);

}
