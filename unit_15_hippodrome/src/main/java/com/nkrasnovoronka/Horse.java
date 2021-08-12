package com.nkrasnovoronka;

import java.util.concurrent.ThreadLocalRandom;

import static com.nkrasnovoronka.tasks.other.race.HorseConstants.*;

public class Horse implements Runnable {

    private final String name;
    private final com.nkrasnovoronka.tasks.other.race.Race race;

    public Horse(String name, com.nkrasnovoronka.tasks.other.race.Race race) {
        this.name = name;
        this.race = race;
    }



    @Override
    public void run() {
        int currentDistance = 0;
        while (currentDistance < FINISH_DISTANCE){
           currentDistance += move(currentDistance);
            sleep();
        }
        race.getFinishPosition().put(race.getPosition().getAndIncrement(), this);
    }

    private int move(int currentDistance){
        int moveDistance = ThreadLocalRandom.current().nextInt(MIN_DISTANCE, MAX_DISTANCE);
        currentDistance += moveDistance;
        return currentDistance;
    }

    private void sleep(){
        int sleepTime = ThreadLocalRandom.current().nextInt(MIN_SLEEP, MAX_SLEEP);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Horse{" +
                "name='" + name + '\'' +
                '}';
    }
}
