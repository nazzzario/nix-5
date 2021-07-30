package com.nkrasnovoronka.controller;

import com.nkrasnovoronka.model.entity.*;
import com.nkrasnovoronka.service.JPATransactionService;
import com.nkrasnovoronka.util.Util;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JPAController {
    private static final  Logger logger = LoggerFactory.getLogger(JPAController.class);
    private final Session session;
    private final String email;
    private final JPATransactionService service;

    public JPAController(Session session, String userEmail) {
        this.session = session;
        email = userEmail;
        service = new JPATransactionService(session);
    }

    public void run(BufferedReader reader) {

        try {
            User currentUser = service.findUserByEmail(email);
            List<Account> accounts = service.findAccountsOfUser(currentUser.getId());;

            accounts.stream().forEach(a -> System.out.format("%s:id -> %s:name", a.getId(), a.getAccountName()));
            System.out.println("Pleas choose account");
            long accountId = Util.chooseAccount(reader, accounts.stream().map(Account::getId).collect(Collectors.toList()));
            Account account = accounts.stream()
                    .filter(a -> a.getId() == accountId)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Entity with id " + accountId + "dosen`t exists"));

            System.out.println("Choose category: ");

            String categories = reader.readLine();

            List<Long> idOfCategories = Collections.singletonList(Long.parseLong(categories));

            System.out.println("Enter amount of money: ");
            long moneyCount = Util.readLong(reader);


            logger.info("Transaction passed");
            System.out.println("\nAll right! Transaction passed");
        } catch (Exception e) {
            logger.error("Transaction wasn`t passed.", e);
            System.out.println(e.getMessage());
            System.out.println("Smth wrong! transaction failed");
        }
    }

    public void createTransaction(Category category, Account account, long moneyCount, Long categoryId)  {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setValue(moneyCount);
//        if (category instanceof ExpenseCategory) {
//            Set<ExpenseCategory> categoryOfOperation = new HashSet<>();
//            service.findExpenseCategoriesByListOfIds(categoryId);
//            transaction.setCategory(categoryOfOperation);
//        } else {
//            Set<IncomeCategory> categoryOfOperation = new HashSet<>();
//            CollectionUtils.addAll(categoryOfOperation, service.findIncomeCategoriesByListOfIds(categoryId));
//            transaction.setCategories(categoryOfOperation);
//        }
        service.save(transaction);
    }
}
