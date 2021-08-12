package com.nkrasnovoronka;

import java.util.*;
import java.util.concurrent.*;
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
        ExecutorService service = Executors.newFixedThreadPool(horses.size());
        List<Future<?>> futures = new ArrayList<>();
        for(Horse h: horses){
            futures.add(service.submit(h));
        }
        for(Future<?> f: futures){
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }
        service.shutdown();
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

