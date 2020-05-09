package com.sinosoft.servicePlatform.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckDataUtil {
	
	@Autowired
	ErrorCodeUtil errorCodeUtil;
	
	/**
	 * 校验字段是否为空
	 * 校验项和校验的名字必须一一对应，如果不设置名字，可设置为""，长度必须一致
	 * @param str 校验项
	 * @param strName 校验项的中文名称
	 * @return
	 * @throws BizException
	 */
	public void checkIsNull(Object[] str, Object[] strName) throws Exception{
		if(str != null && strName != null){
			if(str.length == strName.length){
				for(int i = 0; i < str.length; i++){
					if(str[i] == null || "".equals(str[i])){
						errorCodeUtil.eCode = "#1101";
						errorCodeUtil.msg = "'" + strName[i] + "'字段:'" + str[i] + "',不允许为空！";
						throw new Exception(errorCodeUtil.msg);
					}
				}
			}else{
				errorCodeUtil.eCode = "#0001";
				errorCodeUtil.msg = "校验信息长度不一致";
				throw new Exception(errorCodeUtil.msg);
			}
		}else{
			errorCodeUtil.eCode = "#0002";
			errorCodeUtil.msg = "校验格式不正确";
			throw new Exception(errorCodeUtil.msg);
		}
	}
	
	/**
	 * 校验数组中至少有checkNumMin项有值
	 * 校验项和校验的名字必须一一对应，如果不设置名字，可设置为""，长度必须一致
	 * @param str 校验项
	 * @param strName 校验项的中文名称
	 * @return
	 * @throws BizException
	 */
	public void checkIsNull(Object[] str, Object[] strName,int checkNumMin) throws Exception{
		if(str != null && strName != null){
			int checkNum = 0;
			String errorMessage = "";
			if(str.length == strName.length){
				for(int i = 0; i < str.length; i++){
					if(str[i] == null || "".equals(str[i])){
						errorMessage += strName[i]+",";
					}else{
						checkNum++;
					}
				}
				if(checkNum < checkNumMin){
					errorCodeUtil.eCode = "#1102";
					errorCodeUtil.msg = errorMessage.substring(0, errorMessage.length()-1)+"至少有"+checkNumMin+"项有值";
					throw new Exception(errorCodeUtil.msg);
				}
			}else{
				errorCodeUtil.eCode = "#0001";
				errorCodeUtil.msg = "校验信息长度不一致";
				throw new Exception(errorCodeUtil.msg);
			}
		}else{
			errorCodeUtil.eCode = "#0002";
			errorCodeUtil.msg = "校验格式不正确";
			throw new Exception(errorCodeUtil.msg);
		}
	}
}
