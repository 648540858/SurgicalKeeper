package com.rjkx.sk.admin.om.utils;

import com.rjkx.sk.system.utils.FredaUtils;

/**
 * 
  * @ClassName: VipConsUtils
  * @Description:
  * @author yiyuan-Rally
  * @date 2016年1月13日 下午2:58:32 
  * @version V1.0
 */
public class VipConsUtils 
{
	private static final String ORDER_NUM_VIP_HEADER = "VIP";
	
	private static final String DATE_FORMART_STR = "yyMMddHHmm";
	
	private static volatile int num = 1; 
	
	/**
	 * 生成订单号
	  * 
	  * @author yiyuan-Rally
	  * @date 2016年1月13日 下午2:59:13
	  * @return
	 */
	public static String getOrderNum()
	{
		String str = FredaUtils.getCurrentTime(DATE_FORMART_STR) + String.format("%3d", getNum()).replace(" ", "0"); 
		
		return ORDER_NUM_VIP_HEADER + str;
	}
	
	private static int getNum()
	{
		if(num > 999)
			num = 1;
		return num++;
	}
}
