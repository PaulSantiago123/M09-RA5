import javax.crypto.*;
import java.security.*;
import java.io.*;
import javax.crypto.spec.IvParameterSpec;


public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Hola mon";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
    //Obtenir els bytes de l’String

    byte [] msgBytes = msg.getBytes();

    // Genera IvParameterSpec

    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Genera hash

    

    // Encrypt.

    // Combinar IV i part xifrada.

    // return iv+msgxifrat

     
    }


    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

    // Extreure l'IV.

    // Extreure la part xifrada.

    // Fer hash de la clau

    // Desxifrar.

    // return String desxifrat
    }

    public static void main(String[] args) {
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