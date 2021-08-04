package com.nkrasnovoronka.controller;

import com.nkrasnovoronka.model.entity.Account;
import com.nkrasnovoronka.model.entity.dto.TransactionDTO;
import com.nkrasnovoronka.service.JDBCTransactionService;
import com.nkrasnovoronka.util.CSVTransactionWriter;
import com.nkrasnovoronka.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class JDBCController {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private final JDBCTransactionService service;
    private final String userEmail;

    public JDBCController(Connection connection, String email) {
        this.service = new JDBCTransactionService(connection);
        this.userEmail = email;
    }

    public void run(BufferedReader reader) {
        loggerInfo.info("Starting creating report file");
        try {
            List<Account> accounts = service.getAccountsByUserEmail(userEmail);
            accounts.forEach(a -> System.out.format("%s:id -> %s:name%n", a.getId(), a.getAccountName()));

            System.out.println("Please choose account");
            long accountId = Util.chooseAccount(reader, accounts.stream().map(Account::getId).collect(Collectors.toList()));

            System.out.println("Pleas enter begin date (d/m/yy)");
            Timestamp from = Util.readDate(reader);
            System.out.println("Pleas enter end date (d/m/yy)");
            Timestamp to = Util.readDate(reader);
            List<TransactionDTO> list = service.getTransactions(accountId, from, to);
            String fileName = "report_" + from.toString() + "_" + to.toString() + ".csv";

            CSVTransactionWriter csvTransactionWriter = new CSVTransactionWriter(fileName);
            csvTransactionWriter.createReport(list);

            System.out.println("Report generated successfully in " + fileName);
        } catch (Exception e) {
            loggerError.error("Cannot create report file");
            System.out.println(e.getMessage());
        }
    }
}
