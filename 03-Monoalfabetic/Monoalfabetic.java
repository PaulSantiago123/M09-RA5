import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static final char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
        'V', 'W', 'X', 'Y', 'Z', 
    };

    public static char[] alfabetPermutat;

    public static char[] permutaAlfabet(char [] alfabet) {

        List<Character> abc = new ArrayList(alfabet.length);

        for (char c : alfabet) {
            abc.add(c);
        }
        
        Collections.shuffle(abc);
        char [] alfabetPermutats = new char[abc.size()];

        for (int i = 0; i < abc.size(); i++) {
            alfabetPermutats[i] = abc.get(i);
        }

        return alfabetPermutats;

    }

    public static String xifraMonoAlfa(String cadena){

        String permutat = "";

        if (alfabetPermutat == null) {
            alfabetPermutat = permutaAlfabet(majuscules);
        }

        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            char cMaj = Character.toUpperCase(c);

            int posicio = -1;

            for (int j = 0; j < majuscules.length; j++) {
                char a = majuscules[j];

                if (cMaj == a) {
                    posicio = j; 
                    break;
                }
        
            }
            if (posicio != -1) {
                char cXifrada = alfabetPermutat[posicio];

                if (Character.isLowerCase(c)) {
                    permutat += Character.toLowerCase(cXifrada);
                } else {
                    permutat += cXifrada;
                }
            } else {
                permutat += c;
            }
            
        }

        return permutat;
        
    }

  public static String desxifraMonoAlfa(String cadena) {
        String original = "";

        // NOTA: No llamamos a permutaAlfabet aquí, usamos la clave global existente
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            char cMaj = Character.toUpperCase(c);
            int posicio = -1;

            for (int j = 0; j < alfabetPermutat.length; j++) {
                char a = alfabetPermutat[j];
                if (cMaj == a) {
                    posicio = j; 
                    break;
                }
            }

            if (posicio != -1) {
                char cOriginal = majuscules[posicio];
                if (Character.isLowerCase(c)) {
                    original += Character.toLowerCase(cOriginal);
                } else {
                    original += cOriginal;
                }
            } else {
                original += c;
            }
        }

        return original;
    }

    public static void main(String[] args) {

        String t1 = "Test 01 àrbritre, coixí, Perímetre";
        String t2 = "Test 02 Taüll, DÍA, año";
        String t3 = "Test 03 Peça, Òrrius, Bòvila";

        System.out.println("Xifratge:");
        System.out.println();
    
        String c1 = xifraMonoAlfa(t1);
        String c2 = xifraMonoAlfa(t2);
        String c3 = xifraMonoAlfa(t3);

        System.out.println(t1 + " -> " + c1);
        System.out.println(t2 + " -> " + c2);
        System.out.println(t3 + " -> " + c3);

        System.out.println();
        System.out.println("Desxifratge:");
        System.out.println();

    
        System.out.println(c1 + " -> " + desxifraMonoAlfa(c1));
        System.out.println(c2 + " -> " + desxifraMonoAlfa(c2));
        System.out.println(c3 + " -> " + desxifraMonoAlfa(c3));

      
    }
}