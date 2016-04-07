package com.rjkx.sk.itf.weixin.service;

import java.io.UnsupportedEncodingException;

import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.ResultDataBean;

public interface WeiXinItfServiceV2Itf {

	/**
	 * 获取主用户ID
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月10日 下午2:57:10
	  * @param pDto
	  * @return
	 */
	Dto getMainUserId(Dto pDto) ;
	
	/**
	 * 修改阅读次数++
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月4日 上午11:24:37
	  * @param pDto
	 */
	void editReadCount(Dto pDto);

	/**
	 * 报名
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月4日 上午11:24:59
	  * @param pDto
	 */
	boolean enrollLec(Dto pDto);

	/**
	 * 点赞
	  * 1 + : 2 -
	  * @author yiyuan-Rally
	  * @date 2015年12月4日 上午11:25:51
	  * @param pDto
	 */
	boolean dianCanForLec(Dto pDto);
	/**
	 * 创建用户
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月7日 上午10:38:16
	  * @param pDto
	  * @return
	 */
	String createUser(Dto pDto);
	/**
	 * 添加就诊人
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月7日 下午1:24:46
	  * @param pDto
	 */
	boolean addSicknessPat(Dto pDto) throws Exception;
	
	/**
	 * 修改就诊人
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月7日 下午1:24:46
	  * @param pDto
	 */
	boolean editSicknessPat(Dto pDto) throws Exception;
	
	/**
	 * 个人中心初始化
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月8日 上午9:14:50
	  * @param pDto
	  * @return
	 */
	Dto myCenterInit(Dto pDto) throws Exception ;
	/**
	 * 删除就诊人
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月8日 下午7:04:25
	  * @param pDto
	 */
	void delPat(Dto pDto);
	/**
	 * 自主选医页面查询条件数据初始化
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月9日 上午10:36:24
	  * @param pDto
	  * @return
	 */
	Dto zzQueryPageDataInit(Dto pDto);
	/**
	 * 查询医生
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月9日 下午4:43:49
	  * @param pDto
	  * @return
	 */
	ResultDataBean queryDoc(Dto pDto) throws Exception ;
	/**
	 * 获取医生信息与排期信息
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月10日 上午10:18:14
	  * @param pDto
	  * @return
	 */
	Dto loadDocAndSch(Dto pDto);
	/**
	 * 创建订单..
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月10日 下午8:01:55
	  * @param pDto
	  * @return
	 */
	boolean createOrder(Dto pDto);
	/**
	 * 支付成功后..流程走向..下一节点..
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月14日 下午3:38:37
	  * @param pDto
	  * @return
	 */
	boolean payOrderToNext(Dto pDto);
	/**
	 * 添加VIP会诊
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月11日 上午10:16:45
	  * @param pDto
	 */
	boolean addVipConsultation(Dto pDto) throws UnsupportedEncodingException;
	/**
	 * 修改余额
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月14日 上午10:59:46
	  * @param pDto
	 */
	void editBalance(Dto pDto);
	/**
	 * 余额支付..
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月14日 下午3:59:33
	  * @param pDto
	 */
	boolean payBalance(Dto pDto);
	/**
	 * 退款后走向下一节点
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月14日 下午9:23:53
	  * @param pDto
	 */
	boolean refundToNext(Dto pDto);
	/**
	 * 评分..并支付礼物后..走向结束节点..
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月15日 下午2:41:25
	  * @param pDto
	  * @return
	 */
	boolean pingFenToNext(Dto pDto);
	/**
	 * 添加反馈
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月22日 上午10:38:55
	  * @param pDto
	 */
	boolean addFeedBack(Dto pDto);
	/**
	 * 第一次分享后..增加余额返回true..否则false
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年12月22日 下午1:58:27
	  * @param pDto
	  * @return
	 */
	boolean firstFenXiangAndAddBalance(Dto pDto);
	/**
	 * 获取VIP会诊支付信息
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月18日 下午1:51:04
	  * @param pDto
	  * @return
	 */
	Dto getVipOrderPayInfo(Dto pDto);

	/**
	 * 支付完成后回调
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月18日 下午1:51:45
	  * @param pDto
	  * @return
	 */
	boolean payVipCallBack(Dto pDto);
	
	/***
	 * 清空用户openid
	  * 
	  * @author LiChun
	  * @date 2016年2月8日 上午10:22:48
	 */
	void clearUserOpenId();
	
	/***
	 * 添加用户openid
	  * 
	  * @author LiChun
	  * @date 2016年2月8日 上午10:23:10
	  * @param pDto
	 */
	void addUserOpenId(Dto pDto);
	
	/***
	 * 处理微信消息或事件
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月21日 下午8:57:18
	  * @param pDto
	  * @return
	 */
	String processMessage(Dto pDto);
	
	/***
	 * 获取关注用户列表
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年2月21日 下午8:57:01
	 */
	void getWxUserList();

	/**
	 * 
	 * @Title: 绑定医生 
	 * @author:panlinlin
	 * @data:2016年3月2日
	 * @param pDto
	 * @return
	 * @return: String
	 * @throws Exception 
	 */
	String bindDoc(Dto pDto) throws Exception;
}