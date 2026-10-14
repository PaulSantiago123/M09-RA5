package iticbcn.xifratge;

public class TextXifrat   {

    private final byte[] dades;

    public TextXifrat(byte[] dades) {
        this.dades = dades;
    }

    public byte[] getBytes() {
        return this.dades;
    }

    @Override 
    public String toString() {
        return new String(dades);
    }
        
}
