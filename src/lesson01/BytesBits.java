package lesson01;
import java.util.Scanner;

/*
Write a program which reads one number of type int (say, n). Then it prints
1. Information whether the bit on the 7th position in the bit representation of n
is set.
2. The number of bits set (which are 1) in the bit representation of n.
3. The position of the most significant bit which is set in n (or −1 if none is set).
4. The number which has the same bit representation as n, but with its two least
significant bytes swapped.
Note: Most significant bits (bytes) are those which are written at the left and correspond
to coefficients at the highest powers of 2. Least significant bits (bytes) are those
which are written at the right and correspond to coefficients at the lowest powers of 2.
The positions of bits are traditionally counted from 0 for the least significant bit (so
they correspond to powers of 2).
Your program may have the following form:
 */

public class BytesBits {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an int");
        int n = scan.nextInt();
        scan.close();
        // *1*
        int numberOf1s = 0;
        // your code here
        System.out.println("No. of 1s is " + numberOf1s);
        // *2*
        boolean is7thBitSet = false;
        // your code here
        System.out.println("Is 7th bit set? " + is7thBitSet);
        // *3*
        int mostSignificant = -1;
        // your code here
        System.out.println("Most significant bit set: " +
                mostSignificant);
        // *4*
        int swapped = 0;
        // your code here
        System.out.println("With 2 least significant bytes" +
                " swapped: " + swapped);
    }
}
