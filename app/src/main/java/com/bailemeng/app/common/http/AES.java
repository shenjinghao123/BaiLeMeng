package com.bailemeng.app.common.http;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
public class AES
{
	public static final String sCharSet = "UTF-8";

	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	// private String sKey = Configs.sAesSecret;//key，可自行修改
	private String ivParameter = "0392039203920300";// 偏移量,可自行修改

	private static AES instance = null;

	private AES()
	{

	}

	public static AES getInstance()
	{
		if (instance == null)
			instance = new AES();
		return instance;
	}

	public static String Encrypt(String encData, String secretKey, String vector) throws Exception
	{

		if (secretKey == null)
		{
			return null;
		}
		if (secretKey.length() != 16)
		{
			return null;
		}
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = secretKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));

		return new String(Base64.encode(encrypted, Base64.DEFAULT), "UTF-8");
		// return new Base64.encodeToString(encrypted);// 此处使用BASE64做转码。
	}

	// 加密
	public String encrypt(String sKey, String sSrc) throws Exception
	{
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return new String(Base64.encode(encrypted, Base64.DEFAULT), "UTF-8");
		// return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
	}

	// 解密
	public String decrypt(String sKey, String sSrc) throws Exception
	{
		try
		{
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	public String decrypt(String sSrc, String key, String ivs) throws Exception
	{
		try
		{
			byte[] raw = key.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	public static String encodeBytes(byte[] bytes)
	{
		StringBuffer strBuf = new StringBuffer();

		for (int i = 0; i < bytes.length; i++)
		{
			strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
			strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
		}

		return strBuf.toString();
	}

	public static String AES_Decrypt(String sKey, String sContent)
	{
		try
		{
			return AES.getInstance().decrypt(sKey, sContent);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String AES_Encrypt(String sKey, String sContent)
	{
		try
		{
			return AES.getInstance().encrypt(sKey, sContent);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws Exception
	{
		// 需要加密的字串
		String cSrc = "h";

		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AES.getInstance().encrypt("Z10m1hy4jN25u3U9", cSrc);

		long lUseTime = System.currentTimeMillis() - lStart;
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AES.getInstance().decrypt("Z10m1hy4jN25u3U9", enString);
		lUseTime = System.currentTimeMillis() - lStart;
	}

}