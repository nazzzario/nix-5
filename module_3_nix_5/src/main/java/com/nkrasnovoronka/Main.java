package com.nkrasnovoronka;

import com.nkrasnovoronka.entity.*;
import com.nkrasnovoronka.repository.impl.CategoryRepositoryImpl;
import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;

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

                IncomeCategory c = new IncomeCategory();
                c.setTransaction(transaction);
                c.setDescription("kal");

                session.save(user);
                session.save(account);
                session.save(transaction);
                session.save(category);
                session.save(c);


                CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl(session);
                List<Category> income = categoryRepository.getCategoryByType("income");
                System.out.println(income);
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }
    }
}
