package lesson04.task02;

import java.util.Arrays;
import java.util.Locale;


/*
Define a (functional) generic interface Transform<T,R> declaring one method apply
which takes a T and returns an R.
 */
@FunctionalInterface
interface Transform<T, R> {
    R apply(T s);
}

/*
Define also a class StrToInt which implements
the interface for T=String and R=Integer. The implementation of apply returns
just the length of the string passed as the argument.
 */
class StrToInt implements Transform<String, Integer> {

    @Override
    public Integer apply(String s) {
        return s.length();
    }
}

/*
In the main function create two arrays of the same size and call the transform
function passing the arrays and an implementation of the Transform interface. Do
it in three ways:
• with an object of StrToInt type — types of arrays are then String and Integer;
• with an object of an anonymous class which implements the Transform interface
in such a way that its apply method takes a String and returns its first
character (as Character);
• with a lambda which transforms strings into the same strings but in upper case.

shoud print
[Alice, Sue, Janet, Bea]
[5, 3, 5, 3]
[A, S, J, B]
[ALICE, SUE, JANET, BEA]
 */

public class GenTrans {

    public static void main(String[] args) {
        String[] sin = {"Alice", "Sue", "Janet", "Bea"};
        System.out.println(Arrays.toString(sin) + '\n');

        //• with an object of StrToInt type — types of arrays are then String and Integer;
        Integer[] iout = new Integer[sin.length];
        transform(sin, iout, new StrToInt());
        System.out.println(Arrays.toString(iout));

        //• with an object of an anonymous class which implements the Transform interface
        // in such a way that its apply method takes a String and returns its first
        // character (as Character);
        Character[] cout = new Character[sin.length];
        transform(sin, cout, new Transform<String, Character>() {
            @Override
            public Character apply(String s) {
                return s.charAt(0);
            }
        });
        System.out.println(Arrays.toString(cout));

        String[] sout = new String[sin.length];
        transform(sin, sout, String::toUpperCase);

        System.out.println(Arrays.toString(sout));
    }

    /*
       In the main class define a static function
       private static <T, R>
       void transform(T[] in, R[] out, Transform<T, R> trans) {
            // ...
       }
       which takes two arrays of equal size, one of references of type T and the other of
       type R, and also an object, say trans, implementing the Transform interface. The
       function fills the second array with results of applying the apply function invoked on
       trans to all objects from the first array.
    */
    private static <T, R> void transform(T[] in, R[] out, Transform<T, R> trans) {
        for (int i = 0; i < in.length; i++) {
            out[i] = trans.apply(in[i]);
        }
    }
}
