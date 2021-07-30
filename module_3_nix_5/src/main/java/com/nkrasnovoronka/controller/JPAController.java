package com.nkrasnovoronka.controller;

import com.nkrasnovoronka.model.entity.*;
import com.nkrasnovoronka.service.JPATransactionService;
import com.nkrasnovoronka.util.Util;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

public class JPAController {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    private final Session session;
    private final String email;
    private final JPATransactionService service;

    public JPAController(Session session, String userEmail) {
        this.session = session;
        email = userEmail;
        service = new JPATransactionService(session);
    }

    public void run(BufferedReader reader) {
        loggerInfo.info("Creating new transactions and writing to db");
        try {
            User currentUser = service.findUserByEmail(email);
            List<Account> accounts = service.findAccountsOfUser(currentUser.getId());
            accounts.stream().forEach(a -> System.out.format("%s:id -> %s:name%n", a.getId(), a.getAccountName()));

            System.out.println("Pleas choose account");
            long accountId = Util.chooseAccount(reader, accounts.stream().map(Account::getId).collect(Collectors.toList()));

            Account account = session.find(Account.class, accountId);

            Transaction transaction = new Transaction();
            transaction.setAccount(account);

            System.out.println("Pleas enter transaction value (positive or negative number)");
            Long value = Long.parseLong(reader.readLine());

            List<ExpenseCategory> expenseCategories = service.getExpenseCategories();
            List<IncomeCategory> incomeCategories = service.getIncomeCategories();

            System.out.printf("Pleas choose category by id");
            long categoryId;
            if (value > 0) {
                incomeCategories.forEach(i -> System.out.printf("%s:id -> %s:name%n", i.getId(), i.getName()));
            } else if (value < 0) {
                expenseCategories.forEach(i -> System.out.printf("%s:id -> %s:name%n", i.getId(), i.getName()));
            }
            categoryId = Long.parseLong(reader.readLine());

            service.addTransaction(account, transaction, value, categoryId);
        } catch (Exception e) {
            loggerError.error("Transaction failed", e);
            throw new RuntimeException(e);
        }
    }


}
