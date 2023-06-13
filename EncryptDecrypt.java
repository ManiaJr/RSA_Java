package security;


import java.security.*;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.*;

import java.io.IOException;
import java.nio.file.*;

public class EncryptDecrypt {

    private static String StringPrivateKey="null";
    private static String StringPublicKey="null";

    public static byte[] encrypt(String data) throws BadPaddingException,IllegalBlockSizeException,InvalidKeyException,NoSuchPaddingException,NoSuchAlgorithmException, IOException{
        if(StringPrivateKey.equals("null"))
            StringPrivateKey=new String(Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get("RSA/privateKey"))));
        Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,getPrivateKey(StringPrivateKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data) throws BadPaddingException,IllegalBlockSizeException,InvalidKeyException,NoSuchPaddingException,NoSuchAlgorithmException, IOException, InvalidKeySpecException{
        if(StringPublicKey.equals("null"))
            StringPublicKey=new String(Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get("RSA/publicKey"))));
        Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPublicKey(StringPublicKey));
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data)throws BadPaddingException,IllegalBlockSizeException,InvalidKeyException,NoSuchPaddingException,NoSuchAlgorithmException, IOException, InvalidKeySpecException{
        return decrypt(Base64.getDecoder().decode(data.getBytes()));
    }   

    public static PrivateKey getPrivateKey(String StringPVK) throws InvalidKeyException{
        PrivateKey privateKey=null;
        PKCS8EncodedKeySpec SpecOfKey=new PKCS8EncodedKeySpec(Base64.getDecoder().decode(StringPVK.getBytes()));
        KeyFactory kf=null;
        try{
            kf=KeyFactory.getInstance("RSA");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        try{
            privateKey=kf.generatePrivate(SpecOfKey);
        }catch(InvalidKeySpecException e){
            e.printStackTrace();
        }
        return privateKey;
    }

    public static PublicKey getPublicKey(String StringPUK) throws InvalidKeySpecException{
        PublicKey publicKey=null;
        try{
            X509EncodedKeySpec SpecOfKey=new X509EncodedKeySpec(Base64.getDecoder().decode(StringPUK.getBytes()));
            KeyFactory kf=KeyFactory.getInstance("RSA");
            publicKey=kf.generatePublic(SpecOfKey);
            return publicKey; 
       }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
       }catch(InvalidKeySpecException e){
            e.printStackTrace();
       }
       return publicKey;
    }

}
