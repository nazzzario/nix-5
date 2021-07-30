package com.nkrasnovoronka.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class Util {

    public static long readLong(BufferedReader reader) throws IOException {
        while (true) {
            String text = reader.readLine();
            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                System.out.println("Only long type allowed");
            }
        }

    }

    public static long chooseAccount(BufferedReader reader, List<Long> accountId) throws IOException {
        while (true) {
            long id = readLong(reader);
            if (accountId.contains(id)) {
                return id;
            } else {
                System.out.println("User dosen`t have any accounts");
            }
        }
    }

    public static Timestamp readDate(BufferedReader reader) throws IOException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Timestamp date;
        while (true) {
            try {
                Date dateRead = formatter.parse(reader.readLine());
                date = new Timestamp(dateRead.getTime());
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        }
        return date;
    }

}
