package lesson07.task02.DiffLettWords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Write a program that reads a text (for example, of a book) from a file and using one
chain of stream operations ceates a map where lengths of words are keys, and the
values are lists of non-repeating words from the text with lengths corresponding to
the key, but only those with all letters different (ignoring the case).

Hint: the flatMap stream intermediate operation may be useful. It takes a Function
which transforms each element of this stream into a stream. The flatMap “flattens”
all the resulting streams into one stream of all their elements.
Note: Text files mentioned in the program can be found in my ’public’ directory
(P:/FTP(Public)/werner).

For the text from the file melville_moby_dick.txt the output should look like this:
Two lists of the longest words with all letters different:
length 13: [subordinately]
length 14: [outspreadingly]

and for schultz_sklepy_cynamonowe_UTF8.txt (the format slightly modified to
fit the lines):
Two lists of the longest words with all letters different:
length 13: [bezksiężycową, chłodniejszym, ilustrowanych,
kapelmistrzów, pergaminowych, przykucniętej,
strzelającymi]
length 14: [przykucniętego]
 */
public class DiffLettWords {
    public static void main(String[] args) {
        String book = "/Users/yaroslavshlapak/IdeaProjects/gui/src/lessono07/task02/DiffLettWords/schultz_sklepy_cynamonowe_UTF8.txt";
        //String book = "/Users/yaroslavshlapak/IdeaProjects/gui/src/lessono07/task02/DiffLettWords/melville_moby_dick.txt";

        int minLen = 5; // ignore words shorter than minLen
        /*
        ceates a map where lengths of words are keys, and the
        values are lists of non-repeating words from the text with lengths corresponding to
        the key, but only those with all letters different (ignoring the case).
         */
        try (Stream<String> lines =
                     Files.lines(Paths.get(book)))
        {
            Map<Integer, List<String>> map =
                    lines
                            .filter(line -> line.length() >= minLen)
                            .flatMap(s -> Arrays.stream(s.split("\\P{L}+")).distinct())
                            .filter(s -> s.chars().distinct().count() == s.length())
                            .collect(Collectors.groupingBy(String::length));

            // just printing
            List<Integer> lastTwo =
                    map.keySet().stream().sorted()
                            .collect(Collectors.toList());
            System.out.println("Two lists of the longest " +
                    "words with all letters different:");
            int len = lastTwo.get(lastTwo.size()-2);
            System.out.println("length " + len + ": " +
                    map.get(len));
            len = lastTwo.get(lastTwo.size()-1);
            System.out.println("length " + len + ": " +
                    map.get(len));
        } catch(IOException e) {
            System.out.println("Something wrong...");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static LengthToWord toPair(String s) {
        return new LengthToWord(s.length(), s);
    }


}

class LengthToWord {
    final int length;
    final String word;

    LengthToWord(int length, String word) {
        this.length = length;
        this.word = word;
    }

    public int getLength() {
        return length;
    }

    public String getWord() {
        return word;
    }
}
