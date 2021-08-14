package com.nkrasnovoronka;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private final Set<Horse> horses;
    private final Map<Integer, Horse> finishPosition;
    private final AtomicInteger position;

    public Race() {
        horses = new HashSet<>();
        finishPosition = new ConcurrentHashMap<>();
        position = new AtomicInteger(1);
    }

    public void registerHorseToRace(Horse horse){
        horses.add(horse);
    }

    public synchronized void startRace(){
        System.out.println("Starting race");
        CountDownLatch countDownLatch = new CountDownLatch(horses.size());
        for(Horse h: horses){
            h.setCountDownLatch(countDownLatch);
            new Thread(h).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }
        System.out.println("Race finished");
    }

    public Map<Integer, Horse> getFinishPosition() {
        return finishPosition;
    }

    public AtomicInteger getPosition() {
        return position;
    }

    public Set<Horse> getHorses() {
        return horses;
    }
}

