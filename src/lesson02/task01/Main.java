package lesson02.task01;

/*
Define an interface TwoStringsOper declaring a function apply which takes two
strings and returns a string. Then, define four classes implementing this interface,
where the operation on strings returns:
• their concatenation (class Concat);
• their concatenation, but in the reverse order(class ConcatRev);
• a string consisting of the first letters of the two strings (class Initials);
• their concatenation, but separated by a separator passed to the constructor
(class Separ).

should print:
MaryJohn
JohnMary
MJ
Mary loves John
 */

public class Main {
    public static void main(String[] args) {
        TwoStringsOper[] a = {
                new Concat(), new ConcatRev(),
                new Initials(), new Separ(" loves ")
        };
        for (TwoStringsOper op : a) {
            System.out.println(op.apply("Mary", "John"));
        }
    }
}
