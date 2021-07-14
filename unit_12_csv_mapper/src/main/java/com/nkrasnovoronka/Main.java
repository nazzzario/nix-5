package com.nkrasnovoronka;

import com.nkrasnovoronka.config.CsvMapperConfig;
import com.nkrasnovoronka.model.User;
import com.nkrasnovoronka.parser.CSVData;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CSVData csvData = new CSVData("user.csv");
        CsvMapperConfig csvMapperConfig = new CsvMapperConfig();
        List<User> objectFromCsv = csvMapperConfig.createObjectFromCsv(User.class, csvData);
        objectFromCsv.forEach(System.out::println);

    }
}
