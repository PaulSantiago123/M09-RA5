import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;
    private final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();
    
    public String getSHA512AmbSalt(String pw, String salt) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        
        byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
        
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
        
        } catch (NoSuchAlgorithmException e) {
            return null; 
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        
        int iteracions = 65535; 
        int longitudClau = 512; 
        
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), iteracions, longitudClau);
        
        byte[] bytes = skf.generateSecret(spec).getEncoded();
        
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
        
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        return null; 
    }

    }

    public String forcaBruta(String alg, String hash, String salt) {
    npass = 0;
    char[] chars = CHARSET; 
    char[] contrasenya = new char[6];
    for (char c1 : chars) {
        contrasenya[0] = c1;
        for (char c2 : chars) {
            contrasenya[1] = c2;
            for (char c3 : chars) {
                contrasenya[2] = c3;
                for (char c4 : chars) {
                    contrasenya[3] = c4;
                    for (char c5 : chars) {
                        contrasenya[4] = c5;
                        for (char c6 : chars) {
                            contrasenya[5] = c6;
                            npass++;
                            String contrasenyaActual = new String(contrasenya);
                            String hashGenerat = "";
                        
                            if (alg.equalsIgnoreCase("SHA-512")) {
                                hashGenerat = getSHA512AmbSalt(contrasenyaActual, salt);
                            } else if (alg.equalsIgnoreCase("PBKDF2")) {
                                hashGenerat = getPBKDF2AmbSalt(contrasenyaActual, salt);
                            }
                        
                            if (hashGenerat != null && hashGenerat.equals(hash)) {
                                return contrasenyaActual; 
                            }
 
                        }
                    }
                }
            }
        }
    }
    return null;
}



    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long segons = millis / 1000;
        long minuts = segons / 60;
        long hores = minuts / 60;
        long dies = hores / 24;

        segons = segons % 60;
        minuts = minuts % 60;
        hores = hores % 24;
        long msRestants = millis % 1000;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d ms", 
            dies, hores, minuts, segons, msRestants);
    }

    public static void main(String[] args) throws Exception {
    String salt = "qpoweiruañslkdfjz";
    String pw = "aaabF!";

    Hashes h = new Hashes();

    String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                         h.getPBKDF2AmbSalt(pw, salt) };
    String pwTrobat = null;
    String[] algorismes = {"SHA-512", "PBKDF2"};

    for (int i = 0; i < aHashes.length; i++) {
        System.out.printf("===============================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n", aHashes[i]);
        System.out.printf("-------------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");

        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();

        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("-------------------------------\n\n");
    }
}
}
