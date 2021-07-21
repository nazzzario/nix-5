package com.nkrasnovoronka;

import com.nkrasnovoronka.controller.CSVParserController;

public class Main {
    public static void main(String[] args) {
        CSVParserController csvParserController = new CSVParserController();
        csvParserController.parseCSV();

    }
}
