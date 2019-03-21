import org.junit.Assert;
import org.junit.Test;

public class TestClass {

    @Test
    public void test(){
        SynchronizedCircularListStringBuffer listStringBuffer = new SynchronizedCircularListStringBuffer();

        Writer writer1 = new Writer("1", listStringBuffer);
        Writer writer2 = new Writer("2", listStringBuffer);
        Writer writer3 = new Writer("3", listStringBuffer);

        Reader reader1 = new Reader(listStringBuffer);
        Reader reader2 = new Reader(listStringBuffer);


    }

    @Test
    public void testLogger(){
        SimpleLogger.setHandler("process.log");
        SimpleLogger.write("TEST");
    }
}
