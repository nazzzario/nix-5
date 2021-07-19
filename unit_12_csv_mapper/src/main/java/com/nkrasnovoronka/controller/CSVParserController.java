package com.nkrasnovoronka.controller;

import com.nkrasnovoronka.mapper.CsvMapper;
import com.nkrasnovoronka.model.User;
import com.nkrasnovoronka.parser.CSVData;
import com.nkrasnovoronka.util.UserInput;

import java.util.List;

public class CSVParserController {
    public void parseCSV(){
        String filePath = UserInput.userInputString("Pleas enter csv file path to be parsed");
        CSVData csvData = new CSVData(filePath);
        CsvMapper csvMapperConfig = new CsvMapper();
        List<User> objectFromCsv = csvMapperConfig.createObjectFromCsv(User.class, csvData);
        objectFromCsv.forEach(System.out::println);
    }
}
