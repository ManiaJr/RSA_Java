package security;

import java.io.*;
import java.security.*;

public class RSAKeyPairGenerator {

    private static PrivateKey pvk;
    private static PublicKey puk;
    private static RSAKeyPairGenerator RSAkpg;

    public RSAKeyPairGenerator(int ks) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(ks);
        KeyPair kp = kpg.generateKeyPair();
        RSAKeyPairGenerator.pvk = kp.getPrivate();
        RSAKeyPairGenerator.puk = kp.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File fl = new File(path);
        fl.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(fl);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public static PrivateKey getPrivateKey() {
        return pvk;
    }

    public static PublicKey getPublicKey() {
        return puk;
    }

    public static void StartAndSave(int ks) throws NoSuchAlgorithmException,IOException{
        RSAkpg = new RSAKeyPairGenerator(ks);
        RSAkpg.writeToFile("RSA/publicKey", RSAKeyPairGenerator.getPublicKey().getEncoded());
        RSAkpg.writeToFile("RSA/privateKey", RSAKeyPairGenerator.getPrivateKey().getEncoded());
    }
}