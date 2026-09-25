import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static final char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
        'V', 'W', 'X', 'Y', 'Z', 
    };

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

        char[] alfabetPermutat = permutaAlfabet(majuscules);

        
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
        }

        return permutat;
        
    }

    public static void main(String[] args) {

        System.out.println(xifraMonoAlfa("àrbitre"));
        
    }
}