package com.rjkx.sk.admin.core.test;

import com.rjkx.sk.system.utils.FredaUtils;

public class CoreTest {


	public static void main(String[] args) throws Exception 
	{
		String IMEIStr = "352045066661744";
		
		//String app_id = "rjkxdeskey";
		
		while(true)
		{
			String timeStamp = FredaUtils.getCurrentTime("yymmddHHmmss");
			
			//System.out.println(FredaUtils.encryptBasedDes(IMEIStr + "|" + timeStamp));
			
			String temp = FredaUtils.encryptBasedDes(IMEIStr + "|" + timeStamp);
			
			System.out.println(temp);
			
			System.out.println(temp.substring(0, temp.lastIndexOf("=")));
			
			System.out.println(FredaUtils.decryptBasedDes(temp.substring(0, temp.lastIndexOf("="))));
			
			//System.out.println(FredaUtils.encryptBasedDes("wx" + "|" + timeStamp));
			
			//System.out.println(FredaUtils.encryptBasedDes(IMEIStr3 + "|" + timeStamp));
			
			Thread.sleep(1000);
		}
		
		
	}

}
