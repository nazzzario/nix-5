package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.entity.Account;
import com.nkrasnovoronka.entity.Transaction;
import com.nkrasnovoronka.repository.TransactionRepository;
import org.hibernate.Session;

public class TransactionRepositoryImpl implements TransactionRepository {
    private Session session;

    public TransactionRepositoryImpl(Session session) {
        this.session = session;
    }


    @Override
    public void addTransaction(Account account, Transaction transaction) {
        account.addTransactionToAccount(transaction);
        session.save(transaction);
    }
}
