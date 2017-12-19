package com.sys.volunteer.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptUtil3DES {
	
    //private static final String CRYPT_KEY = "v3VC7LfCq6IL5KgIglqZrQ1b";
	/**
	 * 91KA密钥
	 */
    private static final String CRYPT_KEY = "L5nl416bYhTtEKSYL2NHcmzU";
    private static final String CRYPT_ALGORITHM = "DESede";
    
    /**
     * 3DES解码
     * @param value
     * @return
     */
    public static String decrypt(String value) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] decodedByte = Base64.decodeBase64(value.getBytes());
            byte[] decryptedByte = cipher.doFinal(decodedByte);            
            return new String(decryptedByte);
        } catch(Exception e) {
            return null;
        }
    }
    
    
    /**
     * 3DES编码
     * @param value
     * @return
     */
    public static String encrypt(String value) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            byte[] encryptedByte = cipher.doFinal(value.getBytes());
            byte[] encodedByte = Base64.encodeBase64(encryptedByte);
            return new String(encodedByte);
        } catch(Exception e) {
            return null;
        }
    }
    
    /**
     * 3DES解码
     * @param key 重载了方法,可以动态输入key
     * @param value
     * @return
     */
    public static String decrypt(String key,String value) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            byte[] decodedByte = Base64.decodeBase64(value.getBytes());
            byte[] decryptedByte = cipher.doFinal(decodedByte);            
            return new String(decryptedByte);
        } catch(Exception e) {
            return null;
        }
    }
    
    
    /**
     * 3DES编码
     * @param key 重载了方法,可以动态输入key
     * @param value
     * @return
     */
    public static String encrypt(String key,String value) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), CRYPT_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            byte[] encryptedByte = cipher.doFinal(value.getBytes());
            byte[] encodedByte = Base64.encodeBase64(encryptedByte);
            return new String(encodedByte);
        } catch(Exception e) {
            return null;
        }
    }
}
