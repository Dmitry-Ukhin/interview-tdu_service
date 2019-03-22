package com.interview.tdu_services;

import java.util.HashMap;
import java.util.Map;

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

    public synchronized int putAndGetIndex(String s){
        if (amount == 0){
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

    public synchronized int removeAndGetIndex(){
        if(amount == 0){
            return -1;
        }

        buff[indexFirstAdded] = null;
        if (++indexFirstAdded == capacity){
            indexFirstAdded = 0;
        }
        if(--amount < 0){
            amount = 0;
        }
        return indexFirstAdded-1;
    }

    public boolean isFull(){
        return amount == capacity;
    }

    public String getContentFrom(int index){
        return buff[index];
    }

    public String[] getArrayData(){
        return buff;
    }

    public Map<Integer, String> getStatusCells(){
        Map<Integer, String> map = new HashMap<>();
        for(int i = 0; i < buff.length; i++){
            map.put(i, buff[i]);
        }
        return map;
    }
}
