package iticbcn.xifratge;

public class XifradorRotX {


    public static char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 
        'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 
        'v', 'w', 'x', 'y', 'z', 
    };

    public static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 
        'V', 'W', 'X', 'Y', 'Z', 
    };
    public char caractersTransformar (char c, int numero) {
        for (int i = 0; i < minuscules.length; i++) {
            if (minuscules[i] == c) {
                int novaPosicio = (i + numero) % minuscules.length;
                if (novaPosicio < 0 ) {
                    novaPosicio += minuscules.length;
                }
                return minuscules[novaPosicio];
            } 
        }

        for (int i = 0; i < majuscules.length; i++) {
            if (majuscules[i] == c) {
                int novaPosicio = (i + numero) % majuscules.length;
                if (novaPosicio < 0) {
                    novaPosicio += majuscules.length;
                }
                return majuscules[novaPosicio];
            }
        }

        return c;

    }

    public void forcaBrutaRotX(String cadenaXifrada) {
        for (int i = 0; i < majuscules.length; i++) {
            System.out.println(i +  ": " + desxifraRotX(cadenaXifrada, i));
        }
    }

    public String xifraRotX (String cadena, int desplaçament) {
        String xifrat = "";
        for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                xifrat += caractersTransformar(c, desplaçament);
        }

        return xifrat;
    }

    public String desxifraRotX (String cadena, int desplaçament) {
        String desxifrat = "";
        for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                desxifrat += caractersTransformar(c, -desplaçament);
        }

        return desxifrat;
    }

    public void main (String [] args) {
        System.out.println("Xifrat");
        System.out.println("-------");
        System.out.println("(0)-ABC                   => " + xifraRotX("ABC", 0));
        System.out.println("(2)-XYZ                   => " + xifraRotX("XYZ", 2));
        System.out.println("(4)-Hola, Mr. calçot      => " + xifraRotX("Hola, Mr. calçot", 4));
        System.out.println("(6)-Perdó, per tu què és? => " + xifraRotX("Perdó, per tu què és?", 6));
    
        System.out.println();
    
        System.out.println("Desxifrat");
        System.out.println("---------");
        System.out.println("(0) ABC                   => " + desxifraRotX("ABC", 0));
        System.out.println("(2) ZAÁ                   => " + desxifraRotX("ZAÁ", 2));
        System.out.println("(4) Ïqoc, Óú. écoèqü      => " + desxifraRotX("Ïqoc, Óú. écoèqü", 4));
        System.out.println("(6) Úiüht, úiü wx ùxì ív? => " + desxifraRotX("Úiüht, úiü wx ùxì ív?", 6));
        
        System.out.println();

        System.out.println("Missatge xifrat");
        System.out.println("--------------------");
        forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }
}