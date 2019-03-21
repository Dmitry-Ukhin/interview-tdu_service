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
        for (int i = 1; Application.isRunning(); i++){
            if (i > 10000){
                break;
            }
            try {
                if (buffer.isFull()){
                    Thread.sleep(200);
                    continue;
                }
                int index = buffer.putAndGetIndex("Thread " + name + " wrote data");
                MyLogger.log("Thread-writer "+name+" wrote data into cell "+index);
            }catch (IOException | InterruptedException e){
                System.err.println(e.getMessage());
                break;
            }
            System.out.println("Thread-writer "+name+" iteration: "+i);
        }
    }
}
