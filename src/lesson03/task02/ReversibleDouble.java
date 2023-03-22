package lesson03.task02;

public class ReversibleDouble implements Reversible {
    private double value;
    public ReversibleDouble(double value) {
        this.value = value;
    }
    @Override
    public Reversible reverse() {
        value = 1 / value;
        return this;
    }
    @Override
    public String toString() {
        return "" + value;
    }


}
