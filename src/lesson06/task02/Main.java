package lesson06.task02;

/*
Collatz sequence (known also as hailstone sequence or Ulam sequence) is a sequence
starting from a natural number a0 and whose terms are calculated according
to the rule an+1 = an/2 for even an and an+1 = 3an + 1 for odd an. There is a hypothesis
that such a sequence will always reach 1 (and then will become periodic:
1, 4, 2, 1, 4, 2, 1, 4, . . .). It has been checked up to astronomically great numbers, but
never proved.
For example, if we start from number 5, we get the sequence
[5, 16, 8, 4, 2, 1, . . .],
and starting from 7 the sequence will be longer:
[7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1, . . .].
Your task is to create a class Hailstone, objects of which represent Collatz sequences.
The constructor takes the starting number (a0), which you may assume is a natural
number larger than 1. The objects are iterable, i.e., the class implements interface
Iterable and in each iteration returns next element of the sequence, starting from a0.
The iteration stops after returning, as the last value, the number 1.
Do not use any arrays, strings or collections.


It should print, in one line and separated by spaces, three numbers: the starting value
(ini, in this example 77031), number of steps until 1 is reached (count) and the value
of the maximum element of the sequence (maxel). For example, if the starting value
were 10, the sequence would be [10, 5, 16, 8, 4, 2, 1], and therefore the three numbers
printed by the program would be 10 6 16.
 */
public class Main {
    public static void main(String... args) {
        int ini = 10, count = -1, maxel = 0;
        Hailstone hailstone = new Hailstone(ini);
        for (int h : hailstone) {
            if (h > maxel) maxel = h;
            ++count;
        }
        System.out.println(ini + " " + count + " " + maxel);
    }
}
