package lesson03.task01;

/*
In the main of a separate class, use the static method xminim to find the minima of
a few functions in three different ways:
• passing an object of class Parabola and limits a i b (for example for function
x2 − x + 5/4 for x ∈ [0, 1]);
• passing an object of an anonymous class implementing the FunDD interface
and limits a i b (for example for function
p
(x − 0.75)2 + 1 for x ∈ [0, 2]);
• passing a lambda and limits a i b (for example for function x2(x − 2) for x ∈
[0, 2]).
For the given examples, the results should be 1/2, 3/4 and 4/3 (with high accuracy).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(FunDD.xminim(new Parabola(1.0, -1.0, 1.25), 0, 1));
        System.out.println(FunDD.xminim(new FunDD() {
            @Override
            public double fun(double x) {
                return Math.sqrt((x - 0.75) * (x - 0.75) + 1);
            }
        }, 0, 2));
        System.out.println(FunDD.xminim(x -> x * x * (x - 2), 0, 2));
    }
}
