package com.sinosoft.servicePlatform.util;

import org.springframework.stereotype.Service;

@Service
public class ErrorCodeUtil {
	
	/**
	 * 错误代码
	 * #0000 代表无异常
	 * #0*** 代表系统内部异常
	 * #1*** 代表数据校验异常
	 * #*1** 代表Controoler异常
	 * #*2** 代表Service异常           
	 * #*3** 代表dao异常	#*3*1 代表保存异常  #*3*2 代表删除异常 #*3*3 代表查询异常 #*3*4 代表更新异常
	 */
	public String eCode = "#0000";
	/**
	 * 错误提示信息
	 */
	public String msg = "成功";

	public ErrorCodeUtil(){}
	
	public ErrorCodeUtil(String eCode, String msg){
		this.eCode = eCode;
		this.msg = msg;
	}

	public String geteCode() {
		return eCode;
	}

	public void seteCode(String eCode) {
		this.eCode = eCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
