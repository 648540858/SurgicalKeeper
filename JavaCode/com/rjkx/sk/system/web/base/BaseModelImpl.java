package com.rjkx.sk.system.web.base;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.rjkx.sk.system.dao.FredaDao;
import com.rjkx.sk.system.properties.PropertiesFactory;
import com.rjkx.sk.system.properties.PropertiesFile;
import com.rjkx.sk.system.properties.PropertiesHelper;
@Repository(value="baseModelImpl")
public class BaseModelImpl{
	/**
	 * DAO支持类,有增删改查
	 */
	private FredaDao fredaDao;
	/**
	 * 系统配置文件
	 */
	protected static PropertiesHelper systemProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.SYS_PROPERTIES);
	
	protected static PropertiesHelper appProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.APP_PROPERTIES);
	
	public FredaDao getFredaDao() {
		return fredaDao;
	}
	@Resource(name="fredaDao")
	public void setFredaDao(FredaDao fredaDao) {
		this.fredaDao = fredaDao;
	}
}
