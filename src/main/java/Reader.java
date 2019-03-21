public class Reader extends Thread {
    private String name;
    private SynchronizedCircularListStringBuffer buffer;

    public Reader(SynchronizedCircularListStringBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                int index = buffer.removeAndGetIndex();
                SimpleLogger.setHandler("process.log");
                SimpleLogger.write("Thread "+name+" read data from cell "+index);
            }catch (InterruptedException e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
