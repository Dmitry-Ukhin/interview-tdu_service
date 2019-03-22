package com.interview.tdu_services;

import java.io.IOException;

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
                    Thread.sleep(50);
                    continue;
                }
                int index = buffer.putAndGetIndex("Thread " + name + " wrote data");
                MyLogger.log("Thread-writer "+name+" wrote data into cell "+index);
            }catch (IOException | InterruptedException e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
