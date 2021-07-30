package com.nkrasnovoronka.util;

import com.nkrasnovoronka.model.entity.dto.TransactionDTO;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public final class CSVTransactionWriter {
    private String fileName;
    private final String[] header = new String[]{"id", "date", "value", "categoryType", "accountName"};

    public CSVTransactionWriter(String fileName) {
        this.fileName = fileName;
    }

    public void createReport(List<TransactionDTO> transactionDTOS) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            writer.writeNext(header);
            writer.writeAll(
                    transactionDTOS.stream()
                            .map(TransactionDTO::toStringArray)
                            .collect(Collectors.toList())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating report");
        }
    }
}
