package security;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.*;
import java.util.Scanner;

public class RSAJava {

    static Scanner scr=new Scanner(System.in);

    public static void main(String[] args) throws NoSuchAlgorithmException,IOException,IllegalBlockSizeException,InvalidKeyException,NoSuchPaddingException,BadPaddingException, InvalidKeySpecException {
        int choice=0;

        System.out.println("1.Low-strength key(64 bytes)\n2.Medium-strength key(128 bytes)\n3.High-strength key(256 bytes)\n4.Very high-strength key(512 bytes)\n0.Exit");
        System.out.print(":");
        choice=scr.nextInt();
        switch(choice){
            case 1:
                RSAKeyPairGenerator.StartAndSave(512);
                RSA();
                break;
            case  2:
                RSAKeyPairGenerator.StartAndSave(1024);
                RSA();
                break;
            case 3:
                RSAKeyPairGenerator.StartAndSave(2048);
                RSA();
                break;
            case 4:
                RSAKeyPairGenerator.StartAndSave(4096);
                RSA();
                break;
            case 0:
                break;
        }
        scr.close();
    }

    public static void RSA() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeySpecException, IOException{
        String keyWord=scr.nextLine();
            do{
                System.out.print("Give me the string that you want to encrypt (Type 'exit' to stop): ");
                try{
                    keyWord=scr.nextLine();
                    if(!keyWord.equals("exit")){
                        String encryptedString = Base64.getEncoder().encodeToString(EncryptDecrypt.encrypt(keyWord));
                        System.out.println("Encrypted message: "+encryptedString);
                        String decryptedString=EncryptDecrypt.decrypt(encryptedString);
                        System.out.println("Decrypted massage: "+decryptedString);
                    }
                }catch(NoSuchAlgorithmException e){
                    System.err.println(e.getMessage());
                }
            }while(!keyWord.equals("exit"));
    }
}
