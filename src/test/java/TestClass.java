import com.interview.tdu_services.*;
import org.junit.Test;

import java.io.IOException;

public class TestClass {
    private SynchronizedCircularListStringBuffer listStringBuffer;

    @Test
    public void test(){
        listStringBuffer = new SynchronizedCircularListStringBuffer();
        try {
            MyLogger.setFile("process.log");
        }catch (IOException e){
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Init threads...");
        Writer writer1 = new Writer("1", listStringBuffer);
        Writer writer2 = new Writer("2", listStringBuffer);
        Writer writer3 = new Writer("3", listStringBuffer);

        Reader reader1 = new Reader("1", listStringBuffer);
        Reader reader2 = new Reader("2", listStringBuffer);

        System.out.println("Start writers...");
        writer1.start();
        writer2.start();
        writer3.start();

        System.out.println("Start readers...");
        reader1.start();
        reader2.start();

        System.out.println("Main wait child threads...");
        try {
            writer1.join();
            writer2.join();
            writer3.join();
            reader1.join();
            reader2.join();
        }catch (InterruptedException e){
            System.err.println(e.getMessage());
        }

        MyLogger.closeFile();
        System.out.println("Main thread ended...");
    }

    @Test
    public void testLogger(){
        SimpleLogger logger = new SimpleLogger();
        logger.setHandler("process.log");
        logger.write("TEST");
    }
}
