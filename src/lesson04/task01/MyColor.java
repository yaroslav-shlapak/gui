package lesson04.task01;

public class MyColor implements Comparable<MyColor> {
    final int red;
    final int green;
    final int blue;

    public MyColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "(" + red + ", " + green + ", " + blue + ")" ;
    }

    @Override
    public int compareTo(MyColor o) {
        int sum1 = this.green + this.red + this.blue;
        int sum2 = o.green + o.red + o.blue;
        return Integer.compare(sum1, sum2);
    }
}
