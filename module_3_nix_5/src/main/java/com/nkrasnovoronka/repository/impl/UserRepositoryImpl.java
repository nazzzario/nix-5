package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.model.entity.Account;
import com.nkrasnovoronka.model.entity.User;
import com.nkrasnovoronka.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public User getUserByEmail(String email) {
        Query<User> query = session.createQuery("select u from User u where u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("User with email %s dosen`t exists", email)));
    }

    @Override
    public List<Account> getAccountsOfUser(Long id) {
        Query<Account> query = session.createQuery("select a from Account a where a.user.id = :id", Account.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
