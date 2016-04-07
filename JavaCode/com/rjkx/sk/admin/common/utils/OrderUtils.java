package com.rjkx.sk.admin.common.utils;

import com.rjkx.sk.system.utils.FredaUtils;

/**
 * 订单工具类
  * @ClassName: OrderUtils
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月12日 上午10:30:02 
  * @version V1.0
 */
public class OrderUtils 
{
	private static final String ORDER_NUM_VIP_HEADER = "V";
	
	private static final String ORDER_NUM_PEOPLE_HEADER = "P";
	
	private static final String DATE_FORMART_STR = "yyMMddHHmmss";
	
	private static volatile int num = 1; 
	
	public static String getOrderNum(boolean isVip)
	{
		String str = FredaUtils.getCurrentTime(DATE_FORMART_STR) + String.format("%3d", getNum()).replace(" ", "0"); 
		
		if(isVip)
		{
			return ORDER_NUM_VIP_HEADER + str;
		}
		else
		{
			return ORDER_NUM_PEOPLE_HEADER + str;
		}
	}
	
	private static int getNum()
	{
		if(num > 99999)
			num = 1;
		return num++;
	}
}
