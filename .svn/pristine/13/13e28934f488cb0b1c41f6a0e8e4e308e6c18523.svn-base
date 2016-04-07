package com.rjkx.sk.system.properties;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;

/**
 * 属性文件工厂类
 * @author Rally
 *
 */
@SuppressWarnings("unchecked")
public class PropertiesFactory {

	private static Log log = LogFactory.getLog(PropertiesFactory.class);
	
	private static Dto container = new BaseDto();
	
	static
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader == null)
		{
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		//系统properties
		try
		{
			InputStream is = classLoader.getResourceAsStream("configs/system/systemConfig.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.SYS_PROPERTIES, ph);
		}
		catch(Exception e)
		{
			log.error("加载系统配置文件出错");
			e.printStackTrace();
		}
		//应用properties
		try
		{
			InputStream is = classLoader.getResourceAsStream("configs/app/appConfig.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.APP_PROPERTIES, ph);
		}
		catch(Exception e)
		{
			log.error("加载应用配置文件出错");
			e.printStackTrace();
		}
	}
	
	/**
     * 获取属性文件实例
     * @param pFile 文件类型
     * @return 返回属性文件实例
     */
	public static PropertiesHelper getPropertiesHelper(String pFile){
		PropertiesHelper ph = (PropertiesHelper)container.get(pFile);
		return ph;
	}
}
