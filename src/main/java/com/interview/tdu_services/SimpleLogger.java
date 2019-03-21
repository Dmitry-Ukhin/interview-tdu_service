package com.interview.tdu_services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class SimpleLogger {
    private File handler;

    public void setHandler(String handler) {
        this.handler = new File(handler);
    }

    public void write(String msg){
        FileWriter writer = null;
        try {
            writer = new FileWriter(handler, true);
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
