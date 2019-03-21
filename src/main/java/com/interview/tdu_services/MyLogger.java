package com.interview.tdu_services;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.*;

public class MyLogger {
    private static FileHandler fileHandler;
    private static Logger logger;

    static {
        logger = Logger.getLogger(MyLogger.class.getName());
    }

    public static void setFile(String fileName) throws IOException{
        fileHandler = new FileHandler(fileName, true);
        logger.addHandler(fileHandler);

        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord logRecord) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date date = new Date(logRecord.getMillis());
                return sdf.format(date)+" >> "+logRecord.getMessage()+"\n";
            }
        });

        fileHandler.setLevel(Level.ALL);
        logger.setLevel(Level.ALL);
    }

    public synchronized static void log(String msg) throws IOException, IllegalStateException{
        if (fileHandler == null) {
            throw new IllegalStateException();
        }

        logger.info(msg);
    }

    public static void closeFile(){
        logger.removeHandler(fileHandler);
        fileHandler.close();
    }
}
