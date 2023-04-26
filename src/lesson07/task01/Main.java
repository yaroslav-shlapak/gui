package lesson07.task01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Write a program which reads a file containing an unknown number of lines which
look like this
Mary 12c 78
Jane 12c 90
Bill 13c 68
Kate 12c 76
John 13c 66
Each line corresponds to a student with a given name, group id and test score. Using
streams, create a map with group ids as keys and list of students belonging to a given
group as values; print these lists. Assuming that toString method in class Student
is appropriately implemented, the output could be something like
Group 13c: [Bill(13c)-68, John(13c)-66]
Group 12c: [Mary(12c)-78, Jane(12c)-90, Kate(12c)-76]
Important: Do not use explicit loops!
 */
public class Main {
    public static void main(String... args) {
        List<String> list = null;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/yaroslavshlapak/IdeaProjects/gui/src/lessono07/task01/input.txt"))) {
            lines
                    .map(Main::toStudent)
                    .collect(Collectors.groupingBy(Student::getGroupId))
                    .forEach((key, value) -> System.out.println(key + " : " + value));
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    static Student toStudent(String line) {
        String[] words = line.split(" ");
        return new Student(words[0], words[1], Integer.parseInt(words[2]));
    }
}

class Student {
    final String name;
    final String groupId;
    final int score;

    Student(String name, String groupId, int score) {
        this.name = name;
        this.groupId = groupId;
        this.score = score;
    }

    String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "name='" + name + "(" + groupId + ")-" + score;
    }
}
