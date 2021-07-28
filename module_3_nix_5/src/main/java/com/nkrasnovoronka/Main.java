package com.nkrasnovoronka;

import com.nkrasnovoronka.entity.*;
import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Instant;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                User user = new User("test", "test", "aa@mail.com");
                Account account = new Account(100d, user);

                Transaction transaction = new Transaction(Instant.now(), 25d );
                account.addTransactionToAccount(transaction);

                ExpenseCategory category = new ExpenseCategory();
                category.setTransaction(transaction);
                category.setDescription("test");

                session.save(user);
                session.save(account);
                session.save(transaction);
                session.save(category);
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }
    }
}
