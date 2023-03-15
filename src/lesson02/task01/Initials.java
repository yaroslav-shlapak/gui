package lesson02.task01;

public class Initials implements TwoStringsOper {
    @Override
    public String apply(String arg1, String arg2) {
        return "" + arg1.charAt(0) + arg2.charAt(0);
    }
}
