package lesson06.task02;

import java.util.Iterator;

/*
Collatz sequence (known also as hailstone sequence or Ulam sequence) is a sequence
starting from a natural number a0 and whose terms are calculated according
to the rule an+1 = an/2 for even an and an+1 = 3an + 1 for odd an.

Your task is to create a class Hailstone, objects of which represent Collatz sequences.
The constructor takes the starting number (a0), which you may assume is a natural
number larger than 1. The objects are iterable, i.e., the class implements interface
Iterable and in each iteration returns next element of the sequence, starting from a0.
The iteration stops after returning, as the last value, the number 1.
Do not use any arrays, strings or collections.
 */
public class Hailstone implements Iterable<Integer> {
    private int an;
    Hailstone(int a0) {
        an = 2 * a0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return an != 1;
            }

            //the rule an+1 = an/2 for even an and an+1 = 3an + 1 for odd an.
            @Override
            public Integer next() {
                if (an % 2 == 0) {
                    an = an / 2;
                    return an;
                } else {
                    an = 3 * an + 1;
                    return an;
                }
            }
        };
    }
}
