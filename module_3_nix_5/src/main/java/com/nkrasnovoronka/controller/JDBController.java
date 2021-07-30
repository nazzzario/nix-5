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
import java.util.ArrayList;
import java.util.List;

public class JDBController {
    private static final  Logger logger = LoggerFactory.getLogger(JDBController.class);
    private final JDBCTransactionService service;
    private final String userEmail;
    private final String fileToExport;
    private final CSVTransactionWriter writer = new CSVTransactionWriter();

    public JDBController(Connection connection, String email, String fileToExport) {
        this.service = new JDBCTransactionService(connection);
        this.userEmail = email;
        this.fileToExport = fileToExport;
    }

    public void run(BufferedReader reader) {
        try {
            List<Account> availableAccounts = service.getAccountsByUserEmail(userEmail);
            List<Long> availableAccountsIds = new ArrayList<>();
            for (Account a : availableAccounts) {
                availableAccountsIds.add(a.getId());
            }
            System.out.println("Enter the id of account: ");
            long id = Util.chooseAccount(reader, availableAccountsIds);
            System.out.println("Enter first date to check transactions history\nDate format: d/m/yy");
            Timestamp from = Util.readDate(reader);
            System.out.println("If you want to current date, press 1, or press 0 to enter your date");
            Timestamp to;
            if (reader.readLine().equals("1"))
                to = new Timestamp(System.currentTimeMillis());
            else {
                System.out.println("To date: ");
                to = Util.readDate(reader);
            }
            List<TransactionDTO> list = service.findTransaction(id, from, to);
            writer.exportReport(fileToExport, list, service.getIncomesAndBalanceForPeriod(list));
            System.out.println("History was saved to file operations.csv");
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
