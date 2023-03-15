package lesson02.task01;

public class ConcatRev implements TwoStringsOper {
    @Override
    public String apply(String arg1, String arg2) {
        return arg2 + arg1;
    }
}
