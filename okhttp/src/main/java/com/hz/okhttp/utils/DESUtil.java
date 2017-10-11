/**
 * =================================================
 *
 * @copyright 杭州龙骞科技有限公司 2012-1014
 * =================================================
 */
package com.hz.okhttp.utils;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密解密
 *
 * @author qfq
 * @date 2013-01-10 11:42:00
 * @version 1.0
 */
public class DESUtil {

    private final static String ALGORITHM = "DES";
    private final static String key ="android*";
    
    
    /**
     * 加密文本（UTF-8编码）
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String encrypt(String text) throws Exception{
    	return encrypt(text,key);
    }

    /**
     * 加密文本（UTF-8编码）
     *
     * @param text
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String text, String key) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHM));
        byte[] data = c.doFinal(text.getBytes("UTF-8"));
        return new String(Base64.encode(data));
    }

    /**
     * 解密文本（UTF-8编码）
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String decrypt(String text){
    	return decrypt(text, key);
    }
    
    /**
     * 解密文本（UTF-8编码）
     *
     * @param text
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String text, String key) {
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), ALGORITHM));
            byte[] data = c.doFinal(Base64.decode(text.getBytes()));
            return new String(data, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        try {
        	//#debug
            System.out.println(encrypt("1111222222111",UUID.randomUUID().toString().substring(0, 8)));
        } catch (Exception ex) {
            Logger.getLogger(DESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}