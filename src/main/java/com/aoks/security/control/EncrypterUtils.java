package com.aoks.security.control;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public class EncrypterUtils {

    public static void main(String[] args) {

        System.out.println(new EncrypterUtils().digest("md5", "socram").getHexString());

    }
    byte[] password = {00};
    String hexString;

    public EncrypterUtils digest(String algorithm, String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(value.getBytes());
            password = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return this;
    }

    public EncrypterUtils digest(String algorithm, char[] value) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] tmpBytes = new byte[value.length];
            int count = 0;
            for (char b : value) {
                tmpBytes[count] = (byte) b;
                count++;
            }
            digest.update(tmpBytes);
            password = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getHexString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length; i++) {
            String tmpStr = "0" + Integer.toHexString((0xff & password[i]));
            sb.append(tmpStr.substring(tmpStr.length() - 2));
        }

        return sb.toString();
    }

    public char[] getChar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length; i++) {
            String tmpStr = "0" + Integer.toHexString((0xff & password[i]));
            sb.append(tmpStr.substring(tmpStr.length() - 2));
        }
        return sb.toString().toCharArray();
    }

    public byte[] getBytes() {
        return password;
    }
}
