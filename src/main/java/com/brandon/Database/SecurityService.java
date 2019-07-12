package com.brandon.Database;

import com.brandon.Database.SaltPasswordPair;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class SecurityService {

    private static final SecureRandom secRandom = new SecureRandom();
    private static SecretKeyFactory keyFac;

    public static SaltPasswordPair encryptPassword(String password){
        try {
            if(keyFac == null){
                keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            }
            byte[] salt = new byte[16];
            secRandom.nextBytes(salt);
            KeySpec key = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            return new SaltPasswordPair(salt,keyFac.generateSecret(key).getEncoded());
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }
}
