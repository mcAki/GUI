package com.ages.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class CryptUtil3DES {

	// private static final String CRYPT_KEY = "v3VC7LfCq6IL5KgIglqZrQ1b";
	/**
	 * 91KA密钥
	 */
	public static final String CRYPT_KEY = "L5nl416bYhTtEKSYL2NHcmzU";
	public static final String CRYPT_ALGORITHM = "DESede";

	public static final String CRYPT_ALGORITHM1 = "DESede/ECB/NoPadding";
	public static final String CRYPT_ALGORITHM2 = "DESede/ECB/PKCS5Padding";
	public static final String CRYPT_ALGORITHM3 = "DESede/ECB/PKCS7Padding";
	
	
	/**
	 * 3DES解码
	 * 
	 * @param value
	 * @return
	 */
	public static String decrypt(String value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			byte[] decodedByte = Base64.decodeBase64(value.getBytes());
			byte[] decryptedByte = cipher.doFinal(decodedByte);
			return new String(decryptedByte);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES解码
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] decrypt(byte[] bytes) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			// byte[] decodedByte = Base64.decodeBase64(bytes);
			byte[] decryptedByte = cipher.doFinal(bytes);
			return decryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES编码
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] encrypt(byte[] bytes) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] encryptedByte = cipher.doFinal(bytes);
			// byte[] encodedByte = Base64.encodeBase64(encryptedByte);
			return encryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES编码
	 * 
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(CRYPT_KEY.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] encryptedByte = cipher.doFinal(value.getBytes());
			byte[] encodedByte = Base64.encodeBase64(encryptedByte);
			return new String(encodedByte);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES解码
	 * 
	 * @param key
	 *            重载了方法,可以动态输入key
	 * @param value
	 * @return
	 */
	public static String decrypt(String key, String value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			byte[] decodedByte = Base64.decodeBase64(value.getBytes());
			byte[] decryptedByte = cipher.doFinal(decodedByte);
			return new String(decryptedByte);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES编码
	 * 
	 * @param key
	 *            重载了方法,可以动态输入key
	 * @param value
	 * @return
	 */
	public static String encrypt(String key, String value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] encryptedByte = cipher.doFinal(value.getBytes());
			byte[] encodedByte = Base64.encodeBase64(encryptedByte);
			return new String(encodedByte);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES编码
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] encrypt(String key, byte[] value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] encryptedByte = cipher.doFinal(value);
			// byte[] encodedByte = Base64.encodeBase64(encryptedByte);
			return encryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES编码
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] encrypt(String algorithm, String key, byte[] value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			byte[] encryptedByte = cipher.doFinal(value);
			// byte[] encodedByte = Base64.encodeBase64(encryptedByte);
			return encryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES解码
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] decrypt(String key, byte[] value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(),
					CRYPT_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			// byte[] decodedByte = Base64.decodeBase64(value);
			byte[] decryptedByte = cipher.doFinal(value);
			return decryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 3DES解码
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static byte[] decrypt(String algorithm, String key, byte[] value) {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algorithm);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			// byte[] decodedByte = Base64.decodeBase64(value);
			byte[] decryptedByte = cipher.doFinal(value);
			return decryptedByte;
		} catch (Exception e) {
			return null;
		}
	}

	public static SecretKey toKey(byte[] key) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key, CRYPT_ALGORITHM);
		return keySpec;
	}

	public static byte[] decrypt(byte[] crypt, byte[] key,String algorithm) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(crypt);
	}

	public static byte[] encrypt(byte[] data, byte[] key,String algorithm) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

}
