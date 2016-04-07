package com.rjkx.sk.itf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rjkx.sk.system.utils.FredaUtils;

public class test {

	public static void main(String[] args) throws ParseException {
		String timestamp=FredaUtils.getCurrentTime("yyyyMMddHHmmss");
		String imeiStr="asfsaliw40308508";
		String app_token=CommonItfUtils.getToken(imeiStr, timestamp);
		app_token=FredaUtils.decryptBasedDes(app_token);
		System.out.println(app_token);
		String temestamp1=app_token.substring(app_token.lastIndexOf('|')+1);
		System.out.println(temestamp1);
		
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d1 = sdf.parse(timestamp);
		Date d2 = sdf.parse("20160301131712");
		long times1 = d1.getTime();
		long times2 = d2.getTime();
		System.out.println((int)((times1-times2)/1000/60));
	}

}
