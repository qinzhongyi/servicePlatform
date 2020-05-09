package com.sinosoft.servicePlatform.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateIDConfig {
	//用来控制随机字符串个数
	private static int num = 5;
	//用来生成随机数
	private static Random randGen = null;
	//定义随机数格式
	private static char[] numbers = null;
	
	public static String ENVIDFofmat = "env([0-9a-zA-Z]{3})([0-9]{17})([0-9a-zA-Z]{5})";
	public static String USERIDFofmat = "user([0-9a-zA-Z]{2})([0-9]{17})([0-9a-zA-Z]{4})";
	public static String PROIDFofmat = "pro([0-9a-zA-Z]{3})([0-9]{17})([0-9a-zA-Z]{5})";
	public static String TOKENIDFofmat = "sin([0-9a-zA-Z]{3})([0-9]{17})([0-9a-zA-Z]{5})";
	
	/**
	 * 创建envId
	 * @return
	 */
	public static String createEnvId() {
		String str = "env";
		num = 3;
		//创建随机字符串
		str += createRandomString();
		//创建时间
		str += createTime();
		num = 5;
		//创建随机字符串
		str += createRandomString();
		return str;
	}
	
	/**
	 * 创建UserId
	 * @return
	 */
	public static String createUserId() {
		String str = "user";
		num = 2;
		//创建随机字符串
		str += createRandomString();
		//创建时间
		str += createTime();
		num = 4;
		//创建随机字符串
		str += createRandomString();
		return str;
	}
	
	/**
	 * 创建UserId
	 * @return
	 */
	public static String createProjectId() {
		String str = "pro";
		num = 3;
		//创建随机字符串
		str += createRandomString();
		//创建时间
		str += createTime();
		num = 5;
		//创建随机字符串
		str += createRandomString();
		return str;
	}
	
	/**
	 * 创建UserId
	 * @return
	 */
	public static String createToken() {
		String str = "sin";
		num = 3;
		//创建随机字符串
		str += createRandomString();
		//创建时间
		str += createTime();
		num = 5;
		//创建随机字符串
		str += createRandomString();
		return str;
	}

	/**
	 * 生成随机字符串方法
	 * @return
	 */
	private static String  createRandomString(){
		if(randGen == null){
        	randGen = new Random();
		}
    	if(numbers == null){
    		numbers = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                  "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    	}
        char [] randBuffer = new char[num];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbers[randGen.nextInt(71)];
         //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
	}

	/**
	 * 校验sessionId是否正确
	 * @param sessionId
	 * @param userCode
	 * @return
	 */
	public static boolean checkToken(String token){
		boolean bool = false;
		if(token != null || !"".equals(token)){
			Pattern p = Pattern.compile(TOKENIDFofmat);
			Matcher m = p.matcher(token);
			bool = m.find();
		}
		return bool;
	}
	
	/**
	 * 创建时间方法
	 * @return
	 */
	private static String createTime(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
}
