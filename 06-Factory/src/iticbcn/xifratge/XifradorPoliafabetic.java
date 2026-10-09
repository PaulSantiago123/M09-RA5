package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPoliafabetic {

    private static final int clauSecreta = 123;
    public static Random random;
    public static void initRandom (int clau) { random = new Random(clau);}

    public static final char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
        'V', 'W', 'X', 'Y', 'Z', 
    };

     public void permutaAlfabet() {

     List<Character> permutacio = new ArrayList<>(majuscules.length);
     for (char c : majuscules) {
         permutacio.add(c);
     }
     
     Collections.shuffle(permutacio, random);
     for (int i = 0; i < permutacio.size(); i++) {
         alfabetPermutat[i] = permutacio.get(i);
     }

    }

    public static char[] alfabetPermutat = new char[majuscules.length];

    public String xifraPoliAlfa(String msg) {

        String xifrat = "";

        for (int i = 0; i < msg.length(); i++) {
            permutaAlfabet();
            
            char c = msg.charAt(i);
            
            boolean esMinuscula = Character.isLowerCase(c);
            char cMaj = Character.toUpperCase(c);

            int posicio = -1;

            for (int j = 0; j < majuscules.length; j++) {
                if (cMaj == majuscules[j]) {
                    posicio = j;
                    break;
                }
            }


            if (posicio != -1) {

                char cXifrada = alfabetPermutat[posicio];
                if (esMinuscula) {
                    xifrat += Character.toLowerCase(cXifrada);
                } else {
                    xifrat += cXifrada;
                }
            } else {
                xifrat += c;
            }

        }
        return  xifrat;
    }

    public String desxifraPoliAlfa (String msgXifrat) {

        String desxifrat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            
            char c = msgXifrat.charAt(i);
            
            boolean esMinuscula = Character.isLowerCase(c);
            char cMaj = Character.toUpperCase(c);

            int posicio = -1;

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (cMaj == alfabetPermutat[j]) {
                    posicio = j;
                    break;
                }
            }


            if (posicio != -1) {

                char cDesxifrada = majuscules[posicio];
                if (esMinuscula) {
                    desxifrat += Character.toLowerCase(cDesxifrada);
                } else {
                    desxifrat += cDesxifrada;
                }
            } else {
                desxifrat += c;
            }

        }
        return  desxifrat;
    }


    public void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
    
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
    
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
             String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}
