import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SimpleLogger {
    private static File HANDLER;

    public static void setHandler(String handler) {
        SimpleLogger.HANDLER = new File(handler);
    }

    public static void write(String msg){
        FileWriter writer = null;
        try {
            writer = new FileWriter(SimpleLogger.HANDLER);
            writer.write(LocalDateTime.now() + " >> " +msg+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
