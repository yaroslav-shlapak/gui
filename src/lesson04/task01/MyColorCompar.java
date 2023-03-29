package lesson04.task01;

import java.util.Comparator;
/*
Additionally, define a class MyColorCompar implementing the interface Comparator<
MyColor>. The constructor of the class takes an enumerator (enum) of type
ColComponent with three constants: RED, GREEN and BLUE. Depending on the
passed constant, the compare method orders colors according to the appropriate
component.
 */
public class MyColorCompar implements Comparator<MyColor> {
    private final ColComponent colComponent;
    public MyColorCompar(ColComponent colComponent) {
        this.colComponent = colComponent;
    }

    @Override
    public int compare(MyColor color1, MyColor color2) {
        return switch (colComponent) {
            case RED ->
                Integer.compare(color1.red, color2.red);
            case GREEN ->
                Integer.compare(color1.green, color2.green);
            case BLUE ->
                Integer.compare(color1.blue, color2.blue);
        };
    }
}
