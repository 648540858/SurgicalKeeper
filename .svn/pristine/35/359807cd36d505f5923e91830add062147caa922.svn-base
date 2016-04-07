package com.rjkx.sk.system.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.rjkx.sk.system.dao.FredaReader;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
import com.rjkx.sk.system.datastructure.impl.BaseResultDataBean;
import com.rjkx.sk.system.utils.FredaUtils;


public class FredaReaderImpl extends SqlSessionDaoSupport implements FredaReader{
	
	private static Log log = LogFactory.getLog(FredaReaderImpl.class);
	
	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	@Override
	public Object queryForObject(String statementName, Object parameterObject) {
		return super.getSqlSession().selectOne(statementName, parameterObject);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param SQL语句ID号
	 */
	@Override
	public Object queryForObject(String statementName) {
		return super.getSqlSession().selectOne(statementName);
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryForList(String statementName, Object parameterObject) {
		return super.getSqlSession().selectList(statementName, parameterObject);
	}

	/**
	 * 按分页查询
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            查询条件对象(map javaBean)
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List queryForPage(String statementName, Dto qDto) throws SQLException {
		try {
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String start = qDto.getAsString("start");
		String limit = qDto.getAsString("limit");
		int startInt = 0;
		if (FredaUtils.isNotEmpty(start)) {
			startInt = Integer.parseInt(start);
			qDto.put("start", startInt);
		}
		if (FredaUtils.isNotEmpty(limit)) {
			int limitInt = Integer.parseInt(limit);
			qDto.put("end", limitInt);
		}
		
		Integer intStart = qDto.getAsInteger("start");
		Integer end = qDto.getAsInteger("end");
		if (FredaUtils.isEmpty(start) || FredaUtils.isEmpty(end)) {
			try {
				throw new Exception("分页数据中,参数不足!");
			} catch (Exception e) {
				log.error("参数不足..导致出现异常!");
				e.printStackTrace();
			}
		}
		
		return super.getSqlSession().selectList(statementName, qDto, new RowBounds(intStart.intValue(), end.intValue()));
	}
	/**
	 * JSP分页查询
	 * @param statementName 结果SQL语句ID号
	 * @param statementCountName 结果COUNT SQL语句ID号
	 * @param qDto 查询条件对象(map javaBean)
	 * @return
	 * @throws SQLException
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultDataBean queryForPage(String statementName, String statementCountName, Dto qDto) throws SQLException 
	{
		String page = qDto.getAsString("page");
		String limit = qDto.getAsString("limit");
		int startInt = 0;
		if (FredaUtils.isNotEmpty(limit)) {
			int limitInt = Integer.parseInt(limit);
			qDto.put("end", limitInt);
		}
		if (FredaUtils.isNotEmpty(page)) {
			startInt = (Integer.parseInt(page) - 1) * qDto.getAsInteger("end");
			qDto.put("start", startInt);
		}
		Integer intStart = qDto.getAsInteger("start");
		Integer end = qDto.getAsInteger("end");
		if (FredaUtils.isEmpty(page) || FredaUtils.isEmpty(limit)) {
			try {
				throw new Exception("分页数据中,参数不足!");
			} catch (Exception e) {
				log.error("参数不足..导致出现异常!");
				e.printStackTrace();
			}
		}
		List<?> data = super.getSqlSession().selectList(statementName, qDto, new RowBounds(intStart.intValue(), end.intValue()));
		
		Integer count = (Integer)super.getSqlSession().selectOne(statementCountName, qDto);
		
		return new BaseResultDataBean(data , count ,qDto.getAsInteger("page"),qDto.getAsInteger("limit"));
	}

	/**
	 * 查询记录集合
	 * 
	 * @param SQL语句ID号
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryForList(String statementName) {
		return super.getSqlSession().selectList(statementName);
	}

	/**
	 * 获取Connection对象<br>
	 * 说明：虽然向Dao消费端暴露了获取Connection对象的方法但不建议直接获取Connection对象进行JDBC操作
	 * 
	 * @return 返回Connection对象
	 * @throws SQLException
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return super.getSqlSession().getConnection();
	}

	/**
	 * 获取DataSource对象<br>
	 * 说明：虽然向Dao消费端暴露了获取DataSource对象的方法但不建议直接获取DataSource对象进行JDBC操作
	 * 
	 * @return 返回DataSource对象
	 */
	@Override
	public DataSource getDataSourceFromSqlMap() throws SQLException {
		return null;
	}
}
