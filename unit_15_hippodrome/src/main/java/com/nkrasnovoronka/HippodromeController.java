package com.nkrasnovoronka;

public class HippodromeController {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            com.nkrasnovoronka.tasks.other.race.Race race = new com.nkrasnovoronka.tasks.other.race.Race();
            race.registerHorseToRace(new Horse("First", race));
            race.registerHorseToRace(new Horse("Second", race));
            race.registerHorseToRace(new Horse("Third", race));
            race.registerHorseToRace(new Horse("Fourth", race));
            race.registerHorseToRace(new Horse("Fifth", race));
            race.registerHorseToRace(new Horse("Six", race));
            race.registerHorseToRace(new Horse("Seven", race));
            race.registerHorseToRace(new Horse("Eight", race));
            race.registerHorseToRace(new Horse("Nine", race));
            race.registerHorseToRace(new Horse("Ten", race));

            race.startRace();
            System.out.println(race.getFinishPosition());
            System.out.println("-----------------------");
        }
    }
}
