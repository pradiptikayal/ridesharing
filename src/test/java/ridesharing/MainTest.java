package ridesharing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ridesharing.Processor.Processor;

public class MainTest {
    static Processor testPr;
    @BeforeAll
    public static void setUp(){
        testPr = new Processor();
        testPr.setCommand("ADD_DRIVER D1 1 1");
        testPr.processCommand();
        testPr.setCommand("ADD_DRIVER D2 4 5");
        testPr.processCommand();
        testPr.setCommand("ADD_DRIVER D3 2 2");
        testPr.processCommand();
        testPr.setCommand("ADD_RIDER R1 0 0");
        testPr.processCommand();
    }

    @Test
    public void checkMemory(){
        Assertions.assertTrue(testPr.getDriverList().size() == 3);
        Assertions.assertTrue(testPr.getRiderList().size() == 1);
    }

    @Test
    public void testMatchCommand()
    {
        testPr.setCommand("MATCH R1");
        testPr.processCommand();

        Assertions.assertTrue(testPr.getRiderToNearestDrivers().size() == 1);
    }

}
