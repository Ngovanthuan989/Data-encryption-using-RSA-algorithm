import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * KeyPairGenerate
 */
public class KeyPairGenerate {
    public boolean KeyGenerator() {
        try {

            SecureRandom sr = new SecureRandom();
            // Thuat toan phat sinh key-RSA(Rivest Shamir Adleman )
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            kpg.initialize(2048, sr);

            // Tao cap key
            KeyPair kp = kpg.genKeyPair();
            //   Puplic key
            PublicKey pubkey= kp.getPublic();
            //  Private key
            PrivateKey prikey= kp.getPrivate();
            // Tạo folder để chứ key
            File dir=new File("keys");
             if (!dir.exists()) {
                 dir.mkdirs();

             }

            //  Tạo file lưu key trong folder keys vừa tạo


            //  Tạo file lưu public key
            File pubKeyFile= new File("keys/PublicKey.bin");

            if (!pubKeyFile.exists()) {

                pubKeyFile.createNewFile();

                
            }

            // Lưu public key vào file vừa tạo
            FileOutputStream fos= new FileOutputStream(pubKeyFile);

             fos.write(pubkey.getEncoded());

             fos.close();




            //  Tạo file lưu private key
            File priKeyFile= new File("keys/privateKey.bin");

            if (!priKeyFile.exists()) {

                priKeyFile.createNewFile();

                
            }

            // Lưu private key vào file vừa tạo
             fos= new FileOutputStream(priKeyFile);

             fos.write(prikey.getEncoded());

             fos.close();


         System.out.println("Genarate key successfully!!!");
           return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return true;

    }

}