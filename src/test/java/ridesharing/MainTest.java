package ridesharing;
import org.junit.Test;
import com.ridesharing.Processor.Processor;

import org.junit.Assert;
import org.junit.Before;

public class MainTest {
    Processor testPr;
    @Before
    public  void setUp(){
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
        Assert.assertTrue(testPr.getDriverList().size() == 3);
        Assert.assertTrue(testPr.getRiderList().size() == 1);
    }

    @Test
    public void testMatchCommand()
    {
        testPr.setCommand("MATCH R1");
        testPr.processCommand();

        Assert.assertTrue(testPr.getRiderToNearestDrivers().size() == 1);
    }

}
