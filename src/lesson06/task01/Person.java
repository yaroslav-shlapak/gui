package lesson06.task01;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String name;
    private final int birthYear;
    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public static boolean isInColl(Collection<Person> coll, String name, int year) {
        return coll.contains(new Person(name, year));
    }

    @Override
    public int compareTo(Person o) {
        int res = name.compareTo(o.name);
        if (res != 0) {
            return res;
        }
        return Integer.compare(o.birthYear, this.birthYear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return birthYear == person.birthYear && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }
}
