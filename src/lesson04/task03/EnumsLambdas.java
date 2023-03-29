package lesson04.task03;

import java.util.Arrays;
import java.util.Comparator;


/*
should, after supplying missing definitions, print something like
*** Persons by sex and then size ***
Eva(F, XS, Nederland)
Mila(F, S, Deutschland)
Ola(F, M, Polska)
Lina(F, L, Deutschland)
Jan(M, S, Polska)
Max(M, XL, Nederland)
*** Persons by size and then name ***
Eva(F, XS, Nederland)
Jan(M, S, Polska)
Mila(F, S, Deutschland)
Ola(F, M, Polska)
Lina(F, L, Deutschland)
Max(M, XL, Nederland)
*** Countries by name ***
Deutschland
Nederland
 */

public class EnumsLambdas {

    // printArray static function

    public static void main(String[] args) {
        Person[] persons = {
                new Person("Max", Sex.M, Size.XL, Country.NL),
                new Person("Jan", Sex.M, Size.S, Country.PL),
                new Person("Eva", Sex.F, Size.XS, Country.NL),
                new Person("Lina", Sex.F, Size.L, Country.DE),
                new Person("Mila", Sex.F, Size.S, Country.DE),
                new Person("Ola", Sex.F, Size.M, Country.PL),
        };

        Comparator<Person> sexThenSize = (o1, o2) -> {
            return 0;
        };
        Arrays.sort(persons, sexThenSize);
        printArray("Persons by sex and then size", persons);

        Arrays.sort(persons, (o1, o2) -> 0);
        printArray("Persons by size and then name", persons);

        Country[] countries = Country.values();
        Arrays.sort(countries, (o1, o2) -> 0);
        printArray("Countries by name", countries);
    }

    private static void printArray(String s, Object[] objects) {
        System.out.println("*** " + s + " ***");
        for (Object object : objects) {
            System.out.println(object);
        }
    }
}