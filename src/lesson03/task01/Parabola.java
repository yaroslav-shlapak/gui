package lesson03.task01;

/*
Write a class Parabola implementing the FunDD interface with fields a, b and c of
type double in which the method fun calculates the value of the quadratic function
ax2 + bx + c.

 */
public class Parabola implements FunDD {
    private final double a;
    private final double b;
    private final double c;

    public Parabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double fun(double x) {
        return a * x * x + b * x + c;
    }
}
