package com.rjkx.sk.system.dao.impl;
/**
 * DAO支持
 * @author Rally
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.rjkx.sk.system.dao.FredaDao;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.datastructure.impl.BaseResultDataBean;
import com.rjkx.sk.system.exception.PrcException;
import com.rjkx.sk.system.utils.FredaUtils;

public class FredaDaoImpl extends SqlSessionDaoSupport implements FredaDao{

	private static Log log = LogFactory.getLog(FredaDaoImpl.class);
	
	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            要插入的对象(map javaBean)
	 */
	@Override
	public int insert(String statementName, Object parameterObject) {
		return super.getSqlSession().insert(statementName, parameterObject);
	}

	/**
	 * 插入一条记录
	 * 
	 * @param SQL语句ID号
	 */
	@Override
	public int insert(String statementName) {
		return super.getSqlSession().insert(statementName, new BaseDto());
	}

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
		
		Integer count = (Integer)this.queryForObject(statementCountName, qDto);
		
		return new BaseResultDataBean(data , count ,qDto.getAsInteger("page"),qDto.getAsInteger("limit"));
	}
	
	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	@Override
	public int update(String statementName, Object parameterObject) {
		return super.getSqlSession().update(statementName, parameterObject);
	}

	/**
	 * 更新记录
	 * 
	 * @param SQL语句ID号
	 */
	@Override
	public int update(String statementName) {
		return super.getSqlSession().update(statementName, new BaseDto());
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 * @param parameterObject
	 *            更新对象(map javaBean)
	 */
	@Override
	public int delete(String statementName, Object parameterObject) {
		return super.getSqlSession().delete(statementName, parameterObject);
	}

	/**
	 * 删除记录
	 * 
	 * @param SQL语句ID号
	 */
	@Override
	public int delete(String statementName) {
		return super.getSqlSession().delete(statementName, new BaseDto());
	}

	/**
	 * 调用存储过程<br>
	 * 存储过程成功返回标志缺省：appCode=1
	 * 
	 * @param prcName
	 *            存储过程ID号
	 * @param parameterObject
	 *            参数对象(入参、出参)
	 * @throws PrcException
	 *             存储过程调用异常
	 */
	@Override
	public Object callPrc(String prcName, Dto prcDto) throws PrcException {
		
		return super.getSqlSession().selectList(prcName, prcDto);
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

	/**
	 * 获取SqlMapClientTemplate对象<br>
	 * 
	 * @return 返回SqlMapClientTemplate对象
	 */
	@Override
	public SqlSession getSqlMapClientTpl() {
		return super.getSqlSession();
	}
}
