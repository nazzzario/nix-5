package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.entity.Account;
import com.nkrasnovoronka.repository.AccountRepository;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private Session session;

    public AccountRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<Account> getAllByUserId(Long userId) {
        Query query = session.createQuery("select a from Account a");
        return (List<Account>) query.getResultList();
    }
}
