package com.nkrasnovoronka.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
    public static List<String[]> readCsvFile(String filePath) {
        List<String[]> csvFile = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String[] trimSplit = new String[split.length];
                for (int i = 0; i < trimSplit.length; i++) {
                    trimSplit[i] = split[i].trim();
                }
                csvFile.add(trimSplit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvFile;
    }
}
