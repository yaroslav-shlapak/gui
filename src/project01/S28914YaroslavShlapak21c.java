package project01;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/*
Generate (randomly) a text file representing data used by a phone company; the file
contains R lines of the following form
26 121 220
representing phone calls of K company’s customers. The interpretation of the data
is as follows
• first number (26 in the example) is the identification number of the caller (in
range [1,K]);
• second number (121 in the example) is the identification number of the callee
(in range [1,K]); cases of ‘self-calling’ should of course be eliminated;
• third number (220 in the example) represents duration of the call in seconds.
Data wille be more interesting if you use not uniform but, e.g., Gaussian distribution
(Random::nextGaussian), of course shifted and with data outside reasonable ranges
removed.
The program proper reads the data and allows us to get various pieces of information,
for example:
• list of N customers who talked for the longest time as callers (and this time);
• list of N customers who talked for the longest time as callees (and this time);
• list of N customers who called the largest number of other customers;
• list of N customers who received the calls from the largest number of other
customers;
• list of N customers who made the largest number of calls;
• list of N customers who received largest number of calls;
• list of N customers who made the smallest number of calls;
• list of N customers who received the smallest number of calls;
• full information about customer no k: how many calls he/she made and received,
for how many seconds he/she has to pay.
The number of customers (K) can be of the order of 500, number of calls (R) —
something like 200 000.
 */

public class S28914YaroslavShlapak21c {
    public static void main(String... args) {
        String separator = " ";
        //The number of customers (K) can be of the order of 500, number of calls (R) —
        //something like 200 000.
        int numberOfCustomers = 500;
        int numberOfCalls = 200000;

        String filename = "phone_data.txt";

        (new CallsGenerator()).generateCallsData(filename, numberOfCustomers, numberOfCalls, separator);

        // Read the generated data and perform various operations
        try {
            List<Call> calls = (new CallsReader()).readCallsData(filename, separator);
            int numberOfUsers = 4;
            CallsPrinter printer = new CallsPrinter(calls);
            CallsProcessor processor = new CallsProcessor(calls, numberOfUsers);

            // list of N customers who talked for the longest time as callers (and this time)
            List<Integer> longestCallers = processor.getCustomersWithLongestCallTime(true);
            System.out.println("List of " + numberOfUsers + " customers who talked for the longest time as callers:");
            printer.printCustomerInfo(longestCallers);

            // list of N customers who talked for the longest time as callees (and this time)
            List<Integer> longestCallees = processor.getCustomersWithLongestCallTime(false);
            System.out.println("\nList of " + numberOfUsers + " customers who talked for the longest time as callees:");
            printer.printCustomerInfo(longestCallees);

            // list of N customers who called the largest number of other customers
            System.out.println("\nList of " + numberOfUsers + " customers who called the largest number of other customers:");
            List<Integer> mostActiveCallers = processor.getMostActiveCustomers(true);
            printer.printCustomerInfo(mostActiveCallers);

            // list of N customers who received the calls from the largest number of other customers
            System.out.println("\nList of " + numberOfUsers + " customers who received the calls from the largest number of other customers:");
            List<Integer> mostActiveCallees = processor.getMostActiveCustomers(false);
            printer.printCustomerInfo(mostActiveCallees);

            // list of N customers who made the largest number of calls
            System.out.println("\nList of " + numberOfUsers + " customers who made the largest number of calls:");
            List<Integer> mostCallsMade = processor.getCustomersWithMostCalls(true);
            printer.printCustomerInfo(mostCallsMade);

            // list of N customers who received largest number of calls
            System.out.println("\nList of " + numberOfUsers + " customers who received the largest number of calls:");
            List<Integer> mostCallsReceived = processor.getCustomersWithMostCalls(false);
            printer.printCustomerInfo(mostCallsReceived);

            // list of N customers who made the smallest number of calls
            System.out.println("\nList of " + numberOfUsers + " customers who made the smallest number of calls:");
            List<Integer> leastCallsMade = processor.getCustomersWithLeastCalls(true);
            printer.printCustomerInfo(leastCallsMade);

            // list of N customers who received the smallest number of calls
            System.out.println("\nList of " + numberOfUsers + " customers who received the smallest number of calls:");
            List<Integer> leastCallsReceived = processor.getCustomersWithLeastCalls(false);
            printer.printCustomerInfo(leastCallsReceived);

            // full information about customer no k: how many calls he/she made and received,
            // for how many seconds he/she has to pay
            int customerNumber = 42;
            System.out.println("\nFull information about customer no " + customerNumber + ":");
            printer.printCustomerInfo(Collections.singletonList(customerNumber));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class CallsProcessor {
    private final List<Call> calls;
    private final int numberOfUsers;

    // Constructor
    public CallsProcessor(final List<Call> calls, final int numberOfUsers) {
        this.calls = calls;
        this.numberOfUsers = numberOfUsers;
    }

    <T> List<Integer> getCustomers(
            boolean isCaller,
            Collector<Call, ?, T> callCollector,
            Comparator<T> comparator
    ) {
        return calls
                .stream()
                .collect(
                        Collectors.groupingBy(
                                getCorrectUserType(isCaller),
                                callCollector
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .limit(numberOfUsers)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    List<Integer> getMostActiveCustomers(boolean isCaller) {
        return calls
                .stream()
                .map(getCorrectUserType(isCaller))
                .collect(Collectors.toSet())
                .stream()
                .map(id -> {
                    int numberOfConnections = (int) calls.stream()
                            .filter(call -> isCaller ? call.getCaller() == id : call.getCallee() == id)
                            .map(call -> isCaller ? call.getCallee() : call.getCaller())
                            .distinct()
                            .count();
                    return new Connection(id, numberOfConnections);
                })
                .sorted(Comparator.comparing(Connection::getNumberOfConnections).reversed())
                .limit(numberOfUsers)
                .map(Connection::getId)
                .collect(Collectors.toList());
    }

    List<Integer> getCustomersWithLongestCallTime(boolean isCaller) {
        return getCustomers(isCaller, Collectors.summingInt(Call::getDuration), Comparator.reverseOrder());
    }

    // Get the N customers who made/received the smallest number of calls
    List<Integer> getCustomersWithLeastCalls(boolean isCaller) {
        return getCustomers(isCaller, Collectors.counting(), Comparator.naturalOrder());
    }

    // N customers who made/received the largest number of calls
    List<Integer> getCustomersWithMostCalls(boolean isCaller) {
        return getCustomers(isCaller, Collectors.counting(), Comparator.reverseOrder());
    }

    private static Function<Call, Integer> getCorrectUserType(boolean isCaller) {
        return call -> getCorrectUserType(isCaller, call);
    }

    private static int getCorrectUserType(boolean isCaller, Call call) {
        return isCaller ? call.getCaller() : call.getCallee();
    }
}

class CallsGenerator {
    private static final Random random = new Random();

    /*
    representing phone calls of K company’s customers. The interpretation of the data
    is as follows
    • first number (26 in the example) is the identification number of the caller (in
    range [1,K]);
    • second number (121 in the example) is the identification number of the callee
    (in range [1,K]); cases of ‘self-calling’ should of course be eliminated;
    • third number (220 in the example) represents duration of the call in seconds.
    Data wille be more interesting if you use not uniform but, e.g., Gaussian distribution
    (Random::nextGaussian), of course shifted and with data outside reasonable ranges
    removed.
     */
    void generateCallsData(String filename, int numCustomers, int numCalls, String separator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < numCalls; i++) {
                int minId = 1;
                int callerId = getRandomNumber(minId, numCustomers);
                int calleeId = getRandomNumber(minId, numCustomers);
                int duration = getRandomNumberGaussian(3 * 60, 5 * 60);
                int minDuration = 0;
                int maxDuration = 30 * 60;
                boolean isDurationOk = duration >= minDuration && duration <= maxDuration;
                boolean isNotSelfCall = callerId != calleeId;
                if (isNotSelfCall && isDurationOk) {
                    writer.write(callerId + separator + calleeId + separator + duration);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int getRandomNumberGaussian(int std, int mean) {
        return (int) (random.nextGaussian() * std + mean);
    }

    private static int getRandomNumber(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}

class CallsReader {
    List<Call> readCallsData(String filename, String separator) throws IOException {
        List<Call> calls = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(separator);
                if (parts.length == 3) {
                    int caller = Integer.parseInt(parts[0]);
                    int callee = Integer.parseInt(parts[1]);
                    int duration = Integer.parseInt(parts[2]);
                    calls.add(new Call(caller, callee, duration));
                }
            }
        }
        return calls;
    }
}

class CallsPrinter {
    private List<Call> calls;

    // Constructor
    public CallsPrinter(List<Call> calls) {
        this.calls = calls;
    }

    void printCustomerInfo(List<Integer> customers) {
        customers.forEach(customer -> {
            int totalCallsMade = (int) calls.stream()
                    .filter(call -> isCaller(customer, call))
                    .count();

            int totalCallsReceived = (int) calls.stream()
                    .filter(call -> isCallee(customer, call))
                    .count();

            int callDurationAsCaller = calls.stream()
                    .filter(call -> isCaller(customer, call))
                    .mapToInt(Call::getDuration)
                    .sum();

            int callDurationAsCallee = calls.stream()
                    .filter(call -> isCallee(customer, call))
                    .mapToInt(Call::getDuration)
                    .sum();

            System.out.println("Customer " + customer + ":");
            System.out.println("Total calls made: " + totalCallsMade);
            System.out.println("Total calls received: " + totalCallsReceived);
            System.out.println("Total call duration made: " + callDurationAsCaller + " seconds");
            System.out.println("Total call duration received: " + callDurationAsCallee + " seconds");
            System.out.println("*********************************");
        });
    }

    private static boolean isCallee(Integer customer, Call call) {
        return call.getCallee() == customer;
    }

    private static boolean isCaller(Integer customer, Call call) {
        return call.getCaller() == customer;
    }
}

class Call {
    private final int caller;
    private final int callee;
    private final int duration;

    Call(final int caller, final int callee, final int duration) {
        this.caller = caller;
        this.callee = callee;
        this.duration = duration;
    }

    public int getCaller() {
        return caller;
    }

    public int getCallee() {
        return callee;
    }

    public int getDuration() {
        return duration;
    }
}

class Connection {
    private final int id;
    private final long numberOfConnections;

    Connection(final int id, final int numberOfConnections) {
        this.id = id;
        this.numberOfConnections = numberOfConnections;
    }

    public long getNumberOfConnections() {
        return numberOfConnections;
    }

    public int getId() {
        return id;
    }
}
