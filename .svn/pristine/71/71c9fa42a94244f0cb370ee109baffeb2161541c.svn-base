package com.rjkx.sk.manager.basedata.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicDataUtils 
{
	private static final String DATE_TIME = " 00:00:00";
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 将cal的时间转化为系统需要的格式
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 下午1:58:45
	  * @param str
	  * @return
	 */
	public static String formatTimeStrForSch(String str)
	{
		String[] strs = str.split("-");
		
		return strs[2] + "-" + strs[0] + "-" +  strs[1] + DATE_TIME;
	}
	/**
	 * 将时间字符转化为DATE
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 下午1:59:12
	  * @param dateStr
	  * @return
	  * @throws ParseException
	 */
	public static Date dateStrToDate(String dateStr) throws ParseException
	{
		return SDF.parse(dateStr.replaceAll("T", " "));
	}
	/**
	 * 将时间添加15分钟
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 下午2:00:07
	  * @param date
	  * @return
	 */
	public static Date dateAdd15Min(Date date)
	{
		//System.out.println(SDF.format(new Date(date.getTime() + 15 * 60 * 1000)));
		
		return new Date(date.getTime() + 15 * 60 * 1000);
	}
	/**
	 * 将DATE转化成String
	  * 
	  * @author yiyuan-Rally
	  * @date 2015年10月21日 下午2:40:29
	  * @param date
	  * @return
	 */
	public static String getDateStr(Date date)
	{
		return SDF.format(date);
	}
}
