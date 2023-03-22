package lesson03.task01;

public interface FunDD {
    double fun(double x);
    /*
    •   takes a reference f to an object of a class implementing the FunDD interface
        and limits a i b;
    •   finds the value of the argument x from the range [a, b] for which the method
        fun of the object f assumes the minimum value (i.e., finds the location of the
        minimum of the function on [a, b]); a somewhat primitive way to find it would
        be to calculate fun(x) for values of the argument between a and b for every
        value with a (small) fixed step (e.g., 1e-5).
     */
    static double xminim(FunDD f, double a, double b) {
        double step = 1e-5;
        double arg = a;
        double min = f.fun(arg);
        double minX = a;
        while (arg <= b) {
            double fun = f.fun(arg);
            if (fun < min) {
                min = fun;
                minX = arg;
            }
            arg += step;
        }

        return minX;
    }
}
