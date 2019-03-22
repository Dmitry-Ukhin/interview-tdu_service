package com.interview.tdu_services;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

import java.io.IOException;

public class Application {
    private static SynchronizedCircularListStringBuffer buffer;
    private static boolean isRunning;

    static {
        buffer = new SynchronizedCircularListStringBuffer();
    }

    public static void main(String[] args) {
        AbstractHandler handler = new MyHandler();
        JettyServer server = new JettyServer(8001, handler);

        System.out.println("Starting server...");

        try {
            server.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Done...");
    }
    
    public static void run(){
        try {
            MyLogger.setFile("process.log");
        }catch (IOException e){
            System.err.println(e.getMessage());
            stop();
            return;
        }

        if (Application.isRunning()){
            return;
        }
        isRunning = true;

        System.out.println("Init threads...");
        Writer writer1 = new Writer("1", buffer);
        Writer writer2 = new Writer("2", buffer);
        Writer writer3 = new Writer("3", buffer);

        Reader reader1 = new Reader("1", buffer);
        Reader reader2 = new Reader("2", buffer);

        System.out.println("Start writers...");
        writer1.start();
        writer2.start();
        writer3.start();

        System.out.println("Start readers...");
        reader1.start();
        reader2.start();

//        System.out.println("Main wait child threads...");
//        try {
//            writer1.join();
//            writer2.join();
//            writer3.join();
//            reader1.join();
//            reader2.join();
//        }catch (InterruptedException e){
//            System.err.println(e.getMessage());
//        }

//        MyLogger.closeFile();
//        stop();
        System.out.println("Main thread ended...");
    }
    
    public static void stop(){
        isRunning = false;
        MyLogger.closeFile();
    }
    
    public static void setCapacityBuffer(int capacity){
        buffer = new SynchronizedCircularListStringBuffer(capacity);
    }
    
    public static SynchronizedCircularListStringBuffer getBuffer() {
        return buffer;
    }
    
    public static boolean isRunning(){
        return isRunning;
    }
}
