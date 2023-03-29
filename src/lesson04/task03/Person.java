package lesson04.task03;

public class Person {
    public final String name;
    public final Sex sex;
    public final Size size;
    public final Country country;

    public Person(String name, Sex sex, Size size, Country country) {
        this.name = name;
        this.sex = sex;
        this.size = size;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", size=" + size +
                ", country=" + country +
                '}';
    }
}
