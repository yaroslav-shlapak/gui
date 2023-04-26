package lesson08.task01;


/*
Create a class Letters which will be used to run several threads parallely. The
constructor of class Letters takes a string, subsequent letters of which will be printed
by separate threads (as many threads as are letters in the string; each thread prints
‘its’ letters every second).
The main function (of a separate class) creates one object of the class Letters, then
it starts all threads, sleeps for 5 seconds, and then terminates all the threads, as
shown below (do not modify it):

The program should write something like:
Thread A starting
Thread B starting
Thread C starting
Thread D starting
ACDBDBACACDBCBDA
Program completed.
Important:
The methods stop, resume, suspend and destroy from the Thread class are inherently
unsafe and must not be used!
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Letters implements Iterable<Thread> {
    private volatile boolean stop = false;
    List<Thread> threads = new ArrayList<>();

    public Letters(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            Thread thread = new Thread(() -> {
                try {
                    while (!stop) {
                        System.out.print(letter);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            },
                    letter);
            threads.add(thread);
        }
    }

    public void stop() {
        stop = true;
    }

    public void start() {
        stop = false;
        threads.forEach(Thread::start);
    }

    @Override
    public Iterator<Thread> iterator() {
        return threads.iterator();
    }
}

public class Main {
    /*
    The program should write something like:
    Thread A starting
    Thread B starting
    Thread C starting
    Thread D starting
    ACDBDBACACDBCBDA
    Program completed.
     */
    public static void main(String[] args) {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters)
            System.out.println(t.getName() + " starting");
        letters.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        letters.stop();
        System.out.println("\nProgram completed.");
    }
}
