package com.nkrasnovoronka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int userInputNumber(String message) {
        int num;
        System.out.println(message);
        try {
            num = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
            throw new RuntimeException();
        }
        return num;
    }

    public static String userInputString(String message) {
        System.out.println(message);
        String res = "";
        try {
            res = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Sorry wrong input!!!Pleas restart program");
        }
        return res;
    }

}
