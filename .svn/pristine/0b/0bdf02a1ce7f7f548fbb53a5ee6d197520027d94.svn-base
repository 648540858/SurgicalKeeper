package com.rjkx.sk.system.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.rjkx.sk.system.datastructure.PageInfo;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	protected static Log log = LogFactory.getLog(PageInterceptor.class);
	protected static String dialect = "mysql";
	protected static boolean rowBoundsWithCount;

	@SuppressWarnings("unchecked")
	@Override
	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) getValueByFieldName(delegate, "mappedStatement");

//			MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,null,null);
//			RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
//			if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
//				return ivk.proceed();
//			}
			BoundSql boundSql = delegate.getBoundSql();
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
//				log.info("parameterObject error");
//				throw new NullPointerException("parameterObject error");
				ivk.proceed();
			} else {
				PageInfo page = null;
				if (parameterObject instanceof PageInfo) {
					page = (PageInfo) parameterObject;
				} else if (parameterObject instanceof Map) {
					Map<String, Object> map = (Map<String, Object>) parameterObject;
					page = (PageInfo) map.get("page");
					if (page == null){
						return ivk.proceed();
					}
				} else {
					Field pageField = getFieldByFieldName(parameterObject, "page");
					if (pageField != null) {
						page = (PageInfo) getValueByFieldName(parameterObject, "page");
						if (page == null){
							return ivk.proceed();
						}
					} else {
						throw new NoSuchFieldException(parameterObject.getClass().getName());
					}
				}
				int count = 0;
				String sql=boundSql.getSql();
				if(rowBoundsWithCount){
					Connection connection = (Connection) ivk.getArgs()[0];
					String countSql = "select count(0) from (" + sql + ") myCount";
//					log.info("总数sql 语句:" + countSql);
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
					setParameters(countStmt, mappedStatement, countBS, parameterObject);
					ResultSet rs = countStmt.executeQuery();
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
				}
				if (parameterObject instanceof PageInfo) {
					page.setTotalRecord(count);
				} else if (parameterObject instanceof Map) {
					page.setTotalRecord(count);
				} else {
					page.setTotalRecord(count);
					setValueByFieldName(parameterObject, "page", page);					
				}
				String pageSql = generatePageSql(sql, page);
//				log.info("page sql:" + pageSql);
				setValueByFieldName(boundSql, "sql", pageSql);
			}
		}
		return ivk.proceed();
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		rowBoundsWithCount=Boolean.parseBoolean(p.getProperty("rowBoundsWithCount"));
		if (dialect == null || dialect.equals("")) {
			try {
				log.error("dialect property is not found!");
			} catch (Exception e) {
				log.error(e.getMessage());;
			}
		}else{
			dialect=dialect.trim().replace(" ", "").toLowerCase();
		}
	}

	private String generatePageSql(String sql, PageInfo page) {
		int beginRow = 0;
		if (page.getPageNo() > 0) {
			beginRow = (page.getPageNo() - 1) * page.getPageSize();
		}
		if (page != null && (dialect != null || !dialect.equals(""))) {
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + beginRow + "," + page.getPageSize());
			}else if ("postgresql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + page.getPageSize() + " offset " + beginRow);
			} 
			else if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(")  tmp_tb where ROWNUM<=");
				pageSql.append(beginRow + page.getPageSize());
				pageSql.append(") where row_id>");
				pageSql.append(beginRow);
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}
	
	private Field getFieldByFieldName(Object obj, String fieldName) {
		// 从拦截器的注解中获取拦截的类名
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	private Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	private void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}
