package lesson03.task02;

/*
Original:
Cat
2.0
3.0
Dog
Alice in Wonderland
10.0
Reversed:
taC
0.5
0.3333333333333333
goD
dnalrednoW ni ecilA
0.1
Reversed again and modified:
Text: Cat
12.0
13.0
Text: Dog
Text: Alice in Wonderland
20.0
 */
public class Main {
    public static void main(String[] args) {
        Reversible[] revers = new Reversible[] {
                new ReversibleString("Cat"),
                new ReversibleDouble(2),
                new ReversibleDouble(3),
                new ReversibleString("Dog"),
                new ReversibleString("Alice in Wonderland"),
                new ReversibleDouble(10),
        };

        System.out.println("Original:");
        for (Reversible r : revers) System.out.println(r);

        for (Reversible r : revers) { r.reverse(); }

        System.out.println("Reversed:");
        for (Reversible r : revers) System.out.println(r);

        System.out.println("Reversed again and modified:");
        for (Reversible r : revers) {
            r.reverse();
            if (r instanceof ReversibleString) {
                System.out.println("Text: " + r);
            } else if (r instanceof ReversibleDouble) {
                System.out.println(Double.parseDouble(r.toString()) + 10);
            }
        }
    }
}
