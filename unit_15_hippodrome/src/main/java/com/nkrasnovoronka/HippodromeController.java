package com.nkrasnovoronka;

import com.nkrasnovoronka.util.UserInput;

import java.util.Map;

public class HippodromeController {
    public static void main(String[] args) {
        Race race = new Race();
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

        race.getHorses().forEach(System.out::println);
        String userInputString = UserInput.userInputString("Pleas enter horse name");
        race.startRace();
        Map<Integer, Horse> finishPosition = race.getFinishPosition();
        if(userInputString.equals(finishPosition.get(1).getName())){
            System.out.println("Congratulations your horse win");
        }else {
            Map.Entry<Integer, Horse> integerHorseEntry1 = finishPosition.entrySet().stream()
                    .filter(integerHorseEntry -> integerHorseEntry.getValue().getName().equals(userInputString))
                    .findFirst().orElseThrow(RuntimeException::new);
            System.out.println("Sorry your horse finished " + integerHorseEntry1.getKey());
        }
        System.out.println("All horses result = " + finishPosition);
    }
}
