public class SynchronizedCircularListStringBuffer {
    private final String[] buff;
    private int capacity;
    private int indexFirstAdded = -1;
    private int indexLastAdded = -1;
    private int amount;

    public SynchronizedCircularListStringBuffer() {
        this(10);
    }

    public SynchronizedCircularListStringBuffer(int capacity) {
        this.capacity = capacity;
        this.buff = new String[capacity];
    }

    public synchronized int putAndGetIndex(String s) throws InterruptedException{
        if (indexFirstAdded < 0){
            buff[0] = s;
            indexFirstAdded = 0;
            indexLastAdded = 0;
            amount++;
            return indexLastAdded;
        }
        if (indexLastAdded == capacity-1){
            indexLastAdded = -1;
        }
        buff[++indexLastAdded] = s;
        amount++;
        return indexLastAdded;
    }

    public synchronized int removeAndGetIndex() throws InterruptedException{
        String item = buff[indexFirstAdded];
        buff[indexFirstAdded++] = null;
        amount--;
        return indexFirstAdded-1;
    }

    public boolean isFull(){
        return amount == capacity;
    }
}
