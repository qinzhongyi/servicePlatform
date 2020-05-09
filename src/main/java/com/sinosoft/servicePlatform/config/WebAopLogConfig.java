package com.sinosoft.servicePlatform.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.servicePlatform.pojo.vo.UserReqVo;

@Aspect
@Component
public class WebAopLogConfig {

	private static final Logger logger = LoggerFactory.getLogger(WebAopLogConfig.class);

    @Pointcut("execution(public * com.sinosoft.servicePlatform..controller.*.*(..))")//����..����������Ŀ¼����������������..�������в���
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // ���յ����󣬼�¼��������
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // ��¼����������
        logger.info("�����ַ : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("���� : " + Arrays.toString(joinPoint.getArgs()));
        Object[] obj = joinPoint.getArgs();
        for (Object loginItem : obj) {
        	if (loginItem instanceof UserReqVo) {
        		UserReqVo userVo = (UserReqVo) loginItem;
        		userVo.setIp(request.getRemoteAddr());
        	}
        }

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning��ֵ��doAfterReturning�Ĳ�����һ��
    public void doAfterReturning(Object ret) throws Throwable {
        // ���������󣬷�������
    	logger.info("����ֵ : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob Ϊ�����ķ���ֵ
        Class<?> cla = ob.getClass();
        long time = System.currentTimeMillis() - startTime;
        logger.info("��ʱ : {" + time + "}ms");
        String json = JSON.toJSONString(ob);
		JSONObject reqObject = JSONObject.parseObject(json);
		JSONObject properties = reqObject.getJSONObject("head");
		if(properties != null){
			properties.put("time", time);
		}
		ob = reqObject.toJavaObject(cla);
        return ob;
    }
}
