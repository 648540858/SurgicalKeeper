package com.rjkx.sk.system.web.base;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.rjkx.sk.system.dao.FredaReader;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;
import com.rjkx.sk.system.properties.PropertiesFactory;
import com.rjkx.sk.system.properties.PropertiesFile;
import com.rjkx.sk.system.properties.PropertiesHelper;
import com.rjkx.sk.system.utils.SpringBeanLoader;
import com.rjkx.sk.system.utils.SystemCons;
import com.rjkx.sk.system.utils.WebUtil;
/**
 * 所有Controller的父类
 * @author Rally
 *
 */
@Controller
public class BaseController {
	
	//private static Log log = LogFactory.getLog(BaseController.class);
	
	private FredaReader fredaReader;
	
	/**
	 * 系统配置文件
	 */
	protected static PropertiesHelper systemProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.SYS_PROPERTIES);
	
	protected static PropertiesHelper appProperties = PropertiesFactory.getPropertiesHelper(PropertiesFile.APP_PROPERTIES);

	public Object getService(String beanId)
	{
		
		Object springBean = SpringBeanLoader.getSpringBean(beanId);
		
		return springBean;
		
	}
	
	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	/**
	 * 设置一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected void setSessionAttribute(HttpServletRequest request, String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	/**
	 * 移除Session对象属性值
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected void removeSessionAttribute(HttpServletRequest request, String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}
	
	/**
	 * 输出响应
	 * 
	 * @param str
	 * @throws IOException
	 */
	protected void write(String str, HttpServletResponse response) throws IOException {
		response.getWriter().write(str);
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * 
	 * 交易成功提示信息
	 * 
	 * @param pMsg
	 *            提示信息
	 * @param pResponse
	 * @return
	 * @throws IOException 
	 */
	protected void setOkTipMsg(String pMsg, HttpServletResponse response) throws IOException {
		Dto outDto = new BaseDto(SystemCons.TRUE, pMsg);
		write(outDto.toJson(), response);
	}
	
	/**
	 * 
	 * 交易失败提示信息(特指：业务交易失败并不是请求失败)<br>
	 * 和Form的submit中的failur回调对应,Ajax.request中的failur回调是值请求失败
	 * 
	 * @param pMsg
	 *            提示信息
	 * @param pResponse
	 * @return
	 * @throws IOException 
	 */
	protected void setErrTipMsg(String pMsg, HttpServletResponse response) throws IOException {
		Dto outDto = new BaseDto(SystemCons.FALSE, pMsg);
		write(outDto.toJson(), response);
	}
	/**
	 * 将所有前台参数封装成DTO
	 * @param request
	 * @return
	 */
	protected Dto getParamsAsDto(HttpServletRequest request)
	{
		return WebUtil.getPraramsAsDto(request);
	}

	
	/**********************************getter and setter**********************************/
	public FredaReader getFredaReader() {
		return fredaReader;
	}
	@Resource(name="fredaReader")
	public void setFredaReader(FredaReader fredaReader) {
		this.fredaReader = fredaReader;
	}
	
}
