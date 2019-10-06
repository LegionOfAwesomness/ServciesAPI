package com.project.shoponline.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

public class Password {
    // The higher the number of iterations the more 
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int iterations = 20*1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database. 
        Empty passwords are not supported. */
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds 
        to a stored salted hash of the password. */
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndHash = stored.split("\\$");
        if (saltAndHash.length != 2) {
            throw new IllegalStateException(
                "The stored password must have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.encodeBase64String(key.getEncoded());
    }
    
//    private static String revertHash(String password, byte[] salt) throws Exception {
//        if (password == null || password.length() == 0)
//            throw new IllegalArgumentException("Empty passwords are not supported.");
//        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        SecretKey key = f.generateSecret(new PBEKeySpec(
//            password.toCharArray(), salt, iterations, desiredKeyLen));
//        return Base64.decodeBase64(key.getEncoded());
//    }
    
//public static String revertSaltedHash(String stored) throws Exception{
////    String[] saltAndHash = stored.split("\\$");
////    if (saltAndHash.length != 2) {
////        throw new IllegalStateException(
////            "The stored password must have the form 'salt$hash'");
////    }
////    String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
////    return Base64.decodeBase64(saltAndHash[0]);
//    byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
//    // store the salt with the password
//    return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
//}
    
    public static String encryptString(String str) throws Exception{
    	  try {

    		  	// encode the string into a sequence of bytes using the named charset

    		  	// storing the result into a new byte array. 

    		  	byte[] utf8 = str.getBytes("UTF8");

    		byte[] enc = ecipher.doFinal(utf8);

    		// encode to base64

    		enc = BASE64EncoderStream.encode(enc);

    		return new String(enc);

    		  }

    		  catch (Exception e) {

    		  	e.printStackTrace();

    		  }

    		  return null;
    }
    
    public static String decryptString(String str) throws Exception{

    	  try {

    	  	// decode with base64 to get bytes

    	byte[] dec = BASE64DecoderStream.decode(str.getBytes());

    	byte[] utf8 = dcipher.doFinal(dec);

    	// create new string based on the specified charset

    	return new String(utf8, "UTF8");

    	  }

    	  catch (Exception e) {

    	  	e.printStackTrace();

    	  }

    	  return null;
    }
    
	private static Cipher ecipher;
	private static Cipher dcipher;


	public static void generateKey(SecretKey key) {

		try {

			// generate secret key using DES algorithm
			key = KeyGenerator.getInstance("DES").generateKey();

			ecipher = Cipher.getInstance("DES");
			dcipher = Cipher.getInstance("DES");

			// initialize the ciphers with the given key

  ecipher.init(Cipher.ENCRYPT_MODE, key);

  dcipher.init(Cipher.DECRYPT_MODE, key);

		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
			return;
		}
		catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
			return;
		}
		catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
			return;
		}

	}
}