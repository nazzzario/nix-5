package com.nkrasnovoronka.util;

import com.nkrasnovoronka.model.entity.dto.TransactionDTO;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVTransactionWriter {
    public void exportReport(String fileName, List<TransactionDTO> operations, Map<String, Long> transactionInfo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            if (!operations.isEmpty()) {
                writer.writeNext(new String[]{"id", "passed_at", "transaction", "categories"});
                writer.writeAll(convertOperations(operations));
                writer.writeNext(new String[]{"", "", "", ""});
                writer.writeNext(new String[]{"", "", "Income: ", String.valueOf(transactionInfo.get("income"))});
                writer.writeNext(new String[]{"", "", "Balance in period: ", String.valueOf(transactionInfo.get("balance"))});
            }
        } catch (Exception e) {
            throw new RuntimeException("There no operations to be exported");
        }
    }

    private List<String[]> convertOperations(List<TransactionDTO> operations) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC));
        List<String[]> list = new ArrayList<>();
        for (TransactionDTO o : operations) {
            StringBuilder categories = new StringBuilder();
            for (String category : o.getCategories())
                categories.append(category).append("; ");
            list.add(new String[]{
                    String.valueOf(o.getId()),
                    formatter.format(o.getPassedAt()),
                    String.valueOf(o.getTransaction()),
                    categories.toString()
            });
        }
        return list;
    }
}
