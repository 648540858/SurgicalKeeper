package com.rjkx.sk.itf.weixin.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SHA-1加密工具类
 * @author rally
 *
 */
public final class WeiXinMessageDigestUtils 
{
	private static final Log log = LogFactory.getLog(WeiXinMessageDigestUtils.class);
	
	private static final WeiXinMessageDigestUtils instance = new WeiXinMessageDigestUtils();
	
	private MessageDigest alga ;
	
	private WeiXinMessageDigestUtils() 
	{
        try 
        {
            alga = MessageDigest.getInstance("SHA-1");
            
            log.info("alga Instance init!");
        } 
        catch(Exception e) 
        {
            throw new InternalError("init MessageDigest error:" + e.getMessage());
        }
    }
	/**
	 * 获取实例
	 * @return
	 */
	public static WeiXinMessageDigestUtils getInstance()
	{
		return instance;
	}
	
	private static String byte2hex(byte[] b) 
	{
        String des = "";
        
        String tmp = null;
        
        for (int i = 0; i < b.length; i++) 
        {
            tmp = (Integer.toHexString(b[i] & 0xFF));
            
            if (tmp.length() == 1) 
            {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
    /**
     * 加密输出
     * @param strSrc
     * @return
     */
    public String encipher(String strSrc) 
    {
        String strDes = null;
        
        byte[] bt = strSrc.getBytes();
        
        alga.update(bt);
        
        strDes = byte2hex(alga.digest()); //to HexString
        
        log.info("SHA-1 结果:" + strDes);
        
        return strDes;
    }
    
    public static void main(String[] args) 
    {
        String signature="b7982f21e7f18f640149be5784df8d377877ebf9";
        String timestamp="1365760417";
        String nonce="1365691777";
         
        String[] ArrTmp = { "token", timestamp, nonce };
        Arrays.sort(ArrTmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++) {
            sb.append(ArrTmp[i]);
        }
        String pwd =WeiXinMessageDigestUtils.getInstance().encipher(sb.toString());
         
        if (signature.equals(pwd)) {
            System.out.println("token 验证成功~!");
        }else {
            System.out.println("token 验证失败~!");
        }
    }
}
