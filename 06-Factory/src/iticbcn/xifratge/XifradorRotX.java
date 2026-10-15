package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {


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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int rotacio = parsejaClau(clau);
        String resultat = xifraRotX(msg, rotacio);
        return new TextXifrat(resultat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int rotacio = parsejaClau(clau);
        return desxifraRotX(xifrat.toString(), rotacio);
    }

    private int parsejaClau(String clau) throws ClauNoSuportada {
        try {
            int rotacio = Integer.parseInt(clau);
            if (rotacio < 0 || rotacio > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        return rotacio;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

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
}