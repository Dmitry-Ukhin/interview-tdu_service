package com.interview.tdu_services;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.*;

public class MyLogger {
    private static FileHandler fileHandler;
    private static Logger logger;

    private static List<String> buff = new ArrayList<>();

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

    public synchronized static void log(final String msg) throws IllegalStateException{
        if (fileHandler == null) {
            throw new IllegalStateException();
        }

        if (buff.size() < 100){
            buff.add(msg);
        }else{
            new Thread(() -> {
                for (String str :buff) {
                    logger.info(str);
                }
                buff.clear();
                logger.info(msg);
            }).start();
        }
    }

    public static void closeFile(){
        logger.removeHandler(fileHandler);
        fileHandler.close();
    }
}
