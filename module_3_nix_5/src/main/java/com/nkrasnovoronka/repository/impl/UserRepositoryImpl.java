package com.nkrasnovoronka.repository.impl;

import com.nkrasnovoronka.entity.User;
import com.nkrasnovoronka.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public User getUserById(Long userId) {
        Query<User> query = session.createQuery("select u from User u where u.id = :id");
        query.setParameter("id", userId);
        return query.getSingleResult();
    }
}
