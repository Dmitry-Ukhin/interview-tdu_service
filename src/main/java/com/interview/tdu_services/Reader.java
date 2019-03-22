package com.interview.tdu_services;

public class Reader extends Thread {
    private String name;
    private SynchronizedCircularListStringBuffer buffer;

    public Reader(String name, SynchronizedCircularListStringBuffer buffer){
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {

        int i = 0;
        while (true){
            try {
                System.out.println("Reader-"+name+" iteration: "+i++);
                int index = buffer.removeAndGetIndex();
                if (index < 0){
                    continue;
                }

                MyLogger.log("Thread-reader "+name+" read data from cell "+index);
            }catch (IllegalStateException e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
