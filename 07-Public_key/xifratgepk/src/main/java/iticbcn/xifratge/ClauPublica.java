package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;


public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    
    keyGen.initialize(2048);
    
    return keyGen.generateKeyPair();
    }
}
