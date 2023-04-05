package lesson05.task01;

import lesson04.task03.Country;
import lesson04.task03.Person;
import lesson04.task03.Sex;
import lesson04.task03.Size;

import java.lang.invoke.MutableCallSite;
import java.util.*;
import java.util.stream.Collectors;


/*
Write a program creating a map of type
Map<String,List<Car>>
from the data contained in an array of Strings. Each three consecutive elements of
the array specify the name of the salon and the make and price of a car (the price
is given as a String). Names of salons are keys of the map and lists of cars (of type
Car) offered in a given salon are values.
The array arr should not be used after the data has been placed in the map. The
program prints
• in any form, the contents of the map;
• the make, price and salon of the least expensive car.
For the following array (defined at the beginning of the main function):
String[] arr = {
"salon A", "Mercedes", "130000",
"salon B", "Mercedes", "120000",
"salon C", "Ford", "110000",
"salon B", "Opel", "90000",
"salon C", "Honda", "95000",
"salon A", "Ford", "105000",
"salon A", "Renault", "75000"
};
the result should be something like
{salon A=[Mercedes 130000, Ford 105000, Renault 75000],
salon B=[Mercedes 120000, Opel 90000],
salon C=[Ford 110000, Honda 95000]}
Renault in salon A for 75000
 */

public class Main {

    public static void main(String[] args) {
        String[] arr = {
                "salon A", "Mercedes", "130000",
                "salon B", "Mercedes", "120000",
                "salon C", "Ford", "110000",
                "salon B", "Opel", "90000",
                "salon C", "Honda", "95000",
                "salon A", "Ford", "105000",
                "salon A", "Renault", "75000"
        };
        int width = 3;
        Map<String, List<Car>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i += width) {
            String key = arr[i];
            Car value = new Car(arr[i + 1], Integer.parseInt(arr[i + 2]));
            if (map.containsKey(key)) {
                map.get(key).add(value);
            } else {
                ArrayList<Car> list = new ArrayList<>();
                list.add(value);
                map.put(key, list);
            }
        }
        System.out.println(map);
        Car car = null;
        String salon = "";
        for (String key : map.keySet()) {
            List<Car> cars = map.get(key);
            for (Car newCar : cars) {
                if (car == null || newCar.price < car.price) {
                    car = newCar;
                    salon = key;
                }
            }
        }
        System.out.println(car + ", salon: " + salon);
    }
}

class Car {
    String name;
    int price;

    Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}