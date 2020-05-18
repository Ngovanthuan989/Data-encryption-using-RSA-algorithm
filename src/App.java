import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class App {
    public static void main(String[] args) throws Exception {
        KeyPairGenerate keyPairGenerate= new KeyPairGenerate();

        keyPairGenerate.KeyGenerator();

        String str= "Anh Thuan Dep Trai";
        // Mã hóa
        String strEncrypted= App.encryptString(str);
         System.out.println( "Encrypted:" +strEncrypted);

        //  Giải mã
       System.out.println("Decrypted:"+App.decrypt(strEncrypted));
    }

    // Giải mã
    public static String encryptString(String strToEncrypt) {
        try {
            // Dọc file để lấy publickey

            FileInputStream fis = new FileInputStream("keys/publicKey.bin");

            byte[] b = new byte[fis.available()];

            fis.read(b);
            fis.close();

            // Tạo public key

            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            // Mã hóa dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptStringOut = c.doFinal(strToEncrypt.getBytes());

            return Base64.getEncoder().encodeToString(encryptStringOut);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    // Giải mã
    public static String decrypt(String strToDEcrypt) {
        try {
            FileInputStream fis = new FileInputStream("keys/privateKey.bin");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // tạo private key để giải mã

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);

            // Giải mã dữ liệu

            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, priKey);

            byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(strToDEcrypt));

            return new String(decryptOut);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

}
