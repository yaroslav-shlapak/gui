package project01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallsProcessorTest {
    private List<Call> calls;
    private CallsProcessor callsProcessor;

    @BeforeEach
    void setUp() {
        calls = Arrays.asList(
                new Call(1, 2, 120),
                new Call(2, 3, 180),
                new Call(1, 4, 90),
                new Call(3, 1, 150),
                new Call(4, 2, 210),
                new Call(4, 3, 120),
                new Call(4, 3, 0),
                new Call(4, 3, 0),
                new Call(1, 3, 0),
                new Call(6, 2, 0)

        );
        callsProcessor = new CallsProcessor(calls, 3);
    }

    @Test
    void testGetMostActiveCustomers() {
        List<Integer> mostActiveCallers = callsProcessor.getMostActiveCustomers(true);
        assertEquals(Arrays.asList(1, 4, 2), mostActiveCallers);

        List<Integer> mostActiveCallees = callsProcessor.getMostActiveCustomers(false);
        assertEquals(Arrays.asList(2, 3, 1), mostActiveCallees);
    }

    @Test
    void testGetCustomersWithLongestCallTime() {
        List<Integer> customersWithLongestCallTimeAsCaller = callsProcessor.getCustomersWithLongestCallTime(true);
        assertEquals(Arrays.asList(4, 1, 2), customersWithLongestCallTimeAsCaller);

        List<Integer> customersWithLongestCallTimeAsCallee = callsProcessor.getCustomersWithLongestCallTime(false);
        assertEquals(Arrays.asList(2, 3, 1), customersWithLongestCallTimeAsCallee);
    }

    @Test
    void testGetCustomersWithLeastCalls() {
        List<Integer> customersWithLeastCallsAsCaller = callsProcessor.getCustomersWithLeastCalls(true);
        assertEquals(Arrays.asList(2, 3, 6), customersWithLeastCallsAsCaller);

        List<Integer> customersWithLeastCallsAsCallee = callsProcessor.getCustomersWithLeastCalls(false);
        assertEquals(Arrays.asList(1, 4, 2), customersWithLeastCallsAsCallee);
    }

    @Test
    void testGetCustomersWithMostCalls() {
        List<Integer> customersWithMostCallsAsCaller = callsProcessor.getCustomersWithMostCalls(true);
        assertEquals(Arrays.asList(4, 1, 2), customersWithMostCallsAsCaller);

        List<Integer> customersWithMostCallsAsCallee = callsProcessor.getCustomersWithMostCalls(false);
        assertEquals(Arrays.asList(3, 2, 1), customersWithMostCallsAsCallee);
    }
}
