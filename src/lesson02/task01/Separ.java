package lesson02.task01;

public class Separ implements TwoStringsOper {
    private final String separator;
    public Separ(String s) {
        this.separator = s;
    }

    @Override
    public String apply(String arg1, String arg2) {
        return arg1 + separator + arg2;
    }
}
