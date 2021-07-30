package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.model.entity.Account;
import com.nkrasnovoronka.model.entity.Transaction;
import com.nkrasnovoronka.repository.TransactionRepository;
import org.hibernate.Session;

public class TransactionRepositoryImpl implements TransactionRepository {
    private final Session session;

    public TransactionRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(Transaction transaction) {
        org.hibernate.Transaction t = session.getTransaction();
        t.begin();
        try {
            session.persist(t);
            Account account = session.find(Account.class, transaction.getAccount().getId());
            account.setBalance(account.getBalance() + transaction.getValue());
            session.merge(account);
            t.commit();
        } catch (RuntimeException e) {
            t.rollback();
            throw new RuntimeException(e);
        }
    }
}

