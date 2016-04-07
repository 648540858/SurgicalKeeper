package com.rjkx.sk.manager.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjkx.sk.system.datastructure.Dto;
import com.rjkx.sk.system.datastructure.impl.BaseDto;

/**
 * 
  * @ClassName: BasicUtils
  * @Description:
  * @author yiyuan-Rally
  * @date 2015年10月15日 下午4:02:17 
  * @version V1.0
 */
public class BasicUtils 
{
	private static final String[] imgType = {".jpg",".jpeg",".bmp",".gif",".png"};
	/**
	 * 是否为图片类型
	 * @param fileType
	 * @return
	 */
	public static boolean isImg(String fileType)
	{
		boolean boo = false;
		
		for(int i=0;i<imgType.length;i++)
		{
			if(fileType.equals(imgType[i]))
			{
				boo = true;
				break;
			}
		}
		return boo;
	}
	
	/***
	 * 获取数据字典的某key的值
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月23日 上午8:40:26
	  * @param jsonString
	  * @param key
	  * @return
	 */
	public static String getParamVarCode(String jsonString,String varKey) {
		JSONArray jsonArr=JSON.parseArray(jsonString);
		for(int i=0;i<jsonArr.size();i++){
			JSONObject jsonObject=jsonArr.getJSONObject(i);
			if(jsonObject.getString("cVar").equals(varKey)){
				return jsonObject.getString("cCode").trim();
			}
		}
		return null;
	}
	
	/***
	 * 获取数据字典的某key的list值
	  * 
	  * @author yiyuan-LiChun
	  * @date 2016年3月23日 上午8:40:26
	  * @param jsonString
	  * @param key
	  * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Dto> getParamVar(String jsonString,String listKey) {
		List<Dto> data=new ArrayList<Dto>();
		JSONArray jsonArr=JSON.parseArray(jsonString);
		for(int i=0;i<jsonArr.size();i++){
			JSONObject jsonObject=jsonArr.getJSONObject(i);
			if(jsonObject.getString("cList").equals(listKey)){
				Dto wDto=new BaseDto();
				wDto.put("name", jsonObject.getString("cText"));
				wDto.put("code", jsonObject.getString("cCode"));
				data.add(wDto);
			}
		}
		return data;
	}
}
