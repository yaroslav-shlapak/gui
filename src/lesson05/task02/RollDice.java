package lesson05.task02;


/*
main should print something like:
2 6 3
6 5 2 5 5 1
5 3 6 6 6 6 5 2 2 6 4 5 2
6 5 2 3 4 1 6
5 6 2 5 1 4 2 4 6 4 6 4 2 3 3 3 4 6 1
3 5 2 1 1 5 4 5 4 3 6 1 6 5 2 1 1 2 1 2 2 1 5 2 5 1 5
6 1 2 2 1 2 3 4 4
2 3 5 1 6 6 2 2 2 6 5 1 4 5 3 2 5 5 2 2 4 3 4
3 5 6 4 6 4 6 6 1 4
1 4 1 1 6 2 5 5 4 6 1
 */
public class RollDice {
    public static void main(String[] args) {
        for (int turn = 0; turn < 10; ++turn) {
            for (Integer i : new Roll())
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
