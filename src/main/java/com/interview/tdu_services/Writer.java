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
        int i = 0;
        while (true){
            try {
                if (buffer.isFull()){
                    continue;
                }

                int index = buffer.putAndGetIndex("Thread " + name + " wrote data");
                MyLogger.log("Thread-writer "+name+" wrote data into cell "+index);
            }catch (IllegalStateException e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
}
