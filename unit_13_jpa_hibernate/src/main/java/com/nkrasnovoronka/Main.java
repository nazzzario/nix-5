package com.nkrasnovoronka;

import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Query query = session.createQuery("select l from Lesson l join Group g on l.group.id = g.id join Student s on s.group.id = g.id where s.id = :id");
                query.setParameter("id", 1L);
                List resultList = query.getResultList();
                System.out.println(resultList);

                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }
    }
}
