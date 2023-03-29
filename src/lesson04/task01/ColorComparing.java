package lesson04.task01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Create a class MyColor extending java.awt.Color. One constructor will be sufficient;
it should take three color components (red, green, blue) from the range [0, 255] and
refer to the analogous constructor in the base class. The class should define a natural
order based on the sum of components. Also, override the toString method, so the
string representation of a color looks like (red,green,blue), where red, green, blue
are numbers representing the components.

should print
[(1,2,3), (255,0,0), (55,55,100), (10,255,10)]
[(1,2,3), (55,55,100), (255,0,0), (10,255,10)]
[(1,2,3), (10,255,10), (55,55,100), (255,0,0)]
[(255,0,0), (1,2,3), (55,55,100), (10,255,10)]
[(255,0,0), (1,2,3), (10,255,10), (55,55,100)]
 */
public class ColorComparing {
    public static void main(String[] args) {
        List<MyColor> list = Arrays.asList(
                new MyColor(1, 2, 3),
                new MyColor(255, 0, 0),
                new MyColor(55, 55, 100),
                new MyColor(10, 255, 10)
        );
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(
                list, new MyColorCompar(ColComponent.RED));
        System.out.println(list);
        Collections.sort(
                list, new MyColorCompar(ColComponent.GREEN));
        System.out.println(list);
        Collections.sort(
                list, new MyColorCompar(ColComponent.BLUE));
        System.out.println(list);
    }
}