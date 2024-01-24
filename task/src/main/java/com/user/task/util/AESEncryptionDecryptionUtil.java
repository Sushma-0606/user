package com.user.task.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionDecryptionUtil {

	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(AESEncryptionDecryptionUtil.class);

	public static SecretKeySpec secretKey;

	private static final String ALGORITHM = "AES";

	private static byte[] key;
	private static String myKey = "@atozmyct#";

	private static void prepareSecretKey() {
		byte[] key;
		MessageDigest shy = null;
		try {
			key = myKey.getBytes("UTF-8");
			shy = MessageDigest.getInstance("SHA-1");

			key = shy.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, ALGORITHM);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		try {
			prepareSecretKey();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			log.info("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		try {
			prepareSecretKey();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] base64 = Base64.getDecoder().decode(strToDecrypt);
			byte[] cipherCase64 = cipher.doFinal(base64);
			String decryptedValue = new String(cipherCase64);
			return decryptedValue;
		} catch (Exception e) {
			log.info("Error while decrypting: " + e.toString());
			return "";
		}

	}
}
