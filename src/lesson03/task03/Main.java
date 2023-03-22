package lesson03.task03;

import lesson03.task02.Reversible;
import lesson03.task02.ReversibleDouble;
import lesson03.task02.ReversibleString;

import java.util.Arrays;

/*
should print something like
Original    [ 1 5 33 12 98 15 ]
ByVal       [ 1 5 12 15 33 98 ]
ByValRev    [ 98 33 15 12 5 1 ]
ByNumOfDivs [ 1 5 15 33 12 98 ]
BySumOfDigs [ 1 12 5 15 33 98 ]
[Note: you can use enums instead of final static fields, the values of which determine
the comparisons criteria]
 */
public class Main {
    public static void main(String[] args) {
        Integer[] a = {1, 5, 33, 12, 98, 15};
        printTable("Original    ", a);

        Arrays.sort(a, new MyComp(MyComp.BY_VAL));
        printTable("ByVal       ", a);

        Arrays.sort(a, new MyComp(MyComp.BY_VAL_REV));
        printTable("ByValRev    ", a);

        Arrays.sort(a, new MyComp(MyComp.BY_NUM_OF_DIVS));
        printTable("ByNumOfDivs ", a);

        Arrays.sort(a, new MyComp(MyComp.BY_SUM_OF_DIGS));
        printTable("BySumOfDigs ", a);
    }

    static void printTable(String mess, Integer[] a) {
        System.out.print(mess + "[ ");
        for (int d : a) System.out.print(d + " ");
        System.out.print("]\n");
    }
}
