package com.rjkx.sk.system.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLoader {
	
	private static Log log = LogFactory.getLog(SpringBeanLoader.class);
	private static ApplicationContext applicationContext;

	static {
		try {
			initApplicationContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化ApplicationContext对象
	 * @throws Exception 
	 */
	private static void initApplicationContext() throws Exception {
	
		try {
			applicationContext = new ClassPathXmlApplicationContext(new String[] { "configs\\applicationContext.xml","configs\\springmvc.xml"});
		} catch (Exception e) {
			log.error("服务容器初始化失败.");
			e.printStackTrace();
			System.exit(0);
			throw e;
		}
	}

	/**
	 * 返回ApplicationContext对象
	 * 
	 * @return ApplicationContext 返回的ApplicationContext实例
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取一个SpringBean服务
	 * 
	 * @param pBeanId
	 *            Spring配置文件名中配置的SpringID号
	 * @return Object 返回的SpringBean实例
	 */
	public static Object getSpringBean(String pBeanId) {
		Object springBean = null;
		try {
			springBean = applicationContext.getBean(pBeanId);
		} catch (NoSuchBeanDefinitionException e) {
			log.error(e.getMessage());
		}
		log.info("获得Bean:" + springBean.toString() + "成功!" );
		return springBean;
	}

}
