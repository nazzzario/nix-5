package com.nkrasnovoronka;

import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();


                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }
    }
}
