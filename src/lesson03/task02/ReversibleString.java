package lesson03.task02;

public class ReversibleString implements Reversible {
    private String s;
    public ReversibleString(String s) {
        this.s = s;
    }

    @Override
    public Reversible reverse() {
        this.s = new StringBuilder(this.s).reverse().toString();
        return this;
    }

    @Override
    public String toString() {
        return s;
    }
}
