package com.nkrasnovoronka.util;

import com.nkrasnovoronka.model.entity.ExpenseCategory;
import com.nkrasnovoronka.model.entity.IncomeCategory;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class Util {

    public static void expenseTable(List<ExpenseCategory> categories) {
        List<String[]> table = new ArrayList<>();
        table.add(new String[]{"id", "expense"});
        for (ExpenseCategory c : categories) {
            String[] row = new String[2];
            row[0] = c.getId().toString();
            row[1] = c.getName();
            table.add(row);
        }
        for (String[] s : table) {
            System.out.println(Arrays.toString(s));
        }
    }

    public static void incomeTable(List<IncomeCategory> categories) {
        List<String[]> table = new ArrayList<>();
        table.add(new String[]{"id", "income"});
        for (IncomeCategory c : categories) {
            String[] row = new String[2];
            row[0] = c.getId().toString();
            row[1] = c.getName();
            table.add(row);
        }
        for (String[] s : table) {
            System.out.println(Arrays.toString(s));
        }
    }

    public static long readLong(BufferedReader reader) throws IOException {
        while (true) {
            String text = reader.readLine();
            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException e) {
                System.out.println("You must write int");
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
        DateFormat formatter = new SimpleDateFormat("d/M/yy");
        Timestamp date;
        while (true) {
            try {
                Date dateRead = formatter.parse(reader.readLine());
                date = new Timestamp(dateRead.getTime());
                break;
            } catch (ParseException e) {
                System.out.println("Incorrect date");
            }
        }
        return date;
    }

}
