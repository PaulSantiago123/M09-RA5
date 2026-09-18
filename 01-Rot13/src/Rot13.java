public class Rot13 {


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
    public static char caractersTransformar (char c, int numero) {
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

    public static String xifraRot13 (String desxifrat) {
        String xifrat = "";
        for (int i = 0; i < desxifrat.length(); i++) {
                char c = desxifrat.charAt(i);
                xifrat += caractersTransformar(c, 13);
        }

        return xifrat;
    }

    public static String desxifraRot13 (String xifrat) {
        String desxifrat = "";
        for (int i = 0; i < xifrat.length(); i++) {
                char c = xifrat.charAt(i);
                desxifrat += caractersTransformar(c, -13);
        }

        return desxifrat;
    }

    

    public static void main (String [] args) {
        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println("ABC => " + xifraRot13("ABC"));
        System.out.println("XYZ => " + xifraRot13("XYZ"));
        System.out.println("Hola, Mr. calçot => " + xifraRot13("Hola, Mr. calçot"));
        System.out.println("Perdó, per tu què és? => " + xifraRot13("Perdó, per tu què és?"));

        System.out.println("\nDesxifrat");
        System.out.println("---------");
        System.out.println("IÏJ => " + desxifraRot13("IÏJ"));
        System.out.println("FGH => " + desxifraRot13("FGH"));
        System.out.println("Òwúí, Ùá. jiúkwb => " + desxifraRot13("Òwúí, Ùá. jiúkwb"));
        System.out.println("Zmálx, zmá bc acñ nà? => " + desxifraRot13("Zmálx, zmá bc acñ nà?"));
    

    }
}