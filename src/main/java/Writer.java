public class Writer extends Thread {
    private String name;
    private SynchronizedCircularListStringBuffer buffer;

    public Writer(String name, SynchronizedCircularListStringBuffer buffer){
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (buffer.isFull()){
                    Thread.sleep(200);
                    continue;
                }
                int index = buffer.putAndGetIndex("Thread " + name + " wrote data");
                SimpleLogger.setHandler("process.log");
                SimpleLogger.write("Thread "+name+" wrote data into cell "+index);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
