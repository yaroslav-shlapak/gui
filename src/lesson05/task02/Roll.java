package lesson05.task02;

import java.util.Iterator;

/*
Write a class Roll objects of which are iterable, i.e., the class implements the Iterable
interface. Objects of this type (as, for example, objects representing collections) may
be used in “for-each” loops. Therefore, object of our class Roll should also make it
possible. The implementation should be such that iteration over an object of the class
returns random whole numbers from the range [1, 6] (what simulates rolling a die)
until the sum of exactly three last rolls is 11.
[ATTENTION: there must be no loops, strings, arrays or other collections in class
Roll!]
 */
public class Roll implements Iterable<Integer>, Iterator<Integer> {
    int a = 11, b = 11;
    boolean hasNext = true;
    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Integer next() {
        int dice = (int) (Math.random() * 6) + 1;
        if (a + b + dice == 11) {
            hasNext = false;
        } else {
            a = b;
            b = dice;
        }
        return dice;
    }
}
