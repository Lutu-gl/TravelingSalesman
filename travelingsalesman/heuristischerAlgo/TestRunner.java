package travelingsalesman.heuristischerAlgo;


import org.junit.runner.JUnitCore;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(
                //DreiOptTests.class,
                //ZweiOptTests.class,
                MatrixTests.class);
        System.out.println(result.getFailureCount());
        for (Failure e : result.getFailures())
        {
            System.out.println(e.toString());
        }
    }
}
