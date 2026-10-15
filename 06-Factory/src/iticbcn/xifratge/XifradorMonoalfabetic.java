package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic  implements Xifrador {

    public static final char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
        'V', 'W', 'X', 'Y', 'Z', 
    };

    public static char[] alfabetPermutat;


    public XifradorMonoalfabetic() {
        this.alfabetPermutat = permutaAlfabet(majuscules); 
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String resultat = xifraMonoAlfa(msg);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return desxifraMonoAlfa(xifrat.toString());
    }


    public char[] permutaAlfabet(char [] alfabet) {

        List<Character> abc = new ArrayList<>(alfabet.length);

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

    public String xifraMonoAlfa(String cadena){

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

  public String desxifraMonoAlfa(String cadena) {
        String original = "";

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

}