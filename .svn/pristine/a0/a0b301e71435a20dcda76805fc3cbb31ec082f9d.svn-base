package com.rjkx.sk.system.web.base;
/**
 * Service支持类
 * @author Rally
 */
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rjkx.sk.system.dao.FredaDao;
import com.rjkx.sk.system.properties.PropertiesFactory;
import com.rjkx.sk.system.properties.PropertiesFile;
import com.rjkx.sk.system.properties.PropertiesHelper;
import com.rjkx.sk.system.utils.SpringBeanLoader;

@Service(value="baseServiceImpl")
public class BaseServiceImpl {
	/**
	 * model支持类
	 */
	private FredaDao fredaDao;
	/**
	 * 系统配置文件
	 */
	protected static PropertiesHelper systemProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.SYS_PROPERTIES);
	
	protected static PropertiesHelper appProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.APP_PROPERTIES);
	/**
	 * 获得其他Service
	 * @param beanId(String)
	 * @return Object
	 */
	public Object getService(String beanId)
	{
		Object springBean = SpringBeanLoader.getSpringBean(beanId);
		
		return springBean;	
	}
	
	public FredaDao getFredaDao() {
		return fredaDao;
	}
	@Resource(name="fredaDao")
	public void setFredaDao(FredaDao fredaDao) {
		this.fredaDao = fredaDao;
	}

}


