package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Hola mon";

    public byte[] xifraAES(String msg, String clau) throws Exception {
    //Obtenir els bytes de l’String

    byte [] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
   
    // Genera IvParameterSpec

    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(iv);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Genera hash

    MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
    byte[] hash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec secretKeySpec = new SecretKeySpec(hash, ALGORISME_XIFRAT);

    // Encrypt.

    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
    byte[] cipherText = cipher.doFinal(msgBytes);


    // Combinar IV i part xifrada.

    
    byte[] encryptedData = new byte[iv.length + cipherText.length];
    System.arraycopy(iv, 0, encryptedData, 0, iv.length);
    System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

    // return iv+msgxifrat

     return encryptedData;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
     
        // Extreure l'IV.
    System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Extreure la part xifrada.
    int midaMsgXifrat = bIvIMsgXifrat.length - MIDA_IV;
    byte[] msgXifrat = new byte[midaMsgXifrat];
    System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, midaMsgXifrat);

    // Fer hash de la clau
    MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
    byte[] hashClau = md.digest(clau.getBytes(StandardCharsets.UTF_8));
    SecretKeySpec secretKeySpec = new SecretKeySpec(hashClau, ALGORISME_XIFRAT);

    // Desxifrar.
    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
    byte[] msgDesxifratBytes = cipher.doFinal(msgXifrat);

    // return String desxifrat
    return new String(msgDesxifratBytes, StandardCharsets.UTF_8);
    }




    public void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}