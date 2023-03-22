package lesson03.task03;

import java.util.Comparator;

/*
Write a class-comparator MyComp, objects of which can be used for sorting an array
of Integer’s according to various criteria. Desired criterium can be selected by a value
of an integer field initialized in the constructor: possible values of the field should
correspond to the values of static final constants of type int defined in the class:
• BY_VAL: by numerical value, in ascending order;
• BY_VAL_REV: by numerical value, in descending order;
• BY_NUM_OF_DIVS: by number of divisors;
• BY_SUM_OF_DIGS: by sum of digits.
 */
public class MyComp implements Comparator {
    private int value;
    public static final int
            BY_VAL = 0,
            BY_VAL_REV = 1,
            BY_NUM_OF_DIVS = 2,
            BY_SUM_OF_DIGS = 3;

    public MyComp(int value) {
        this.value = value;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
