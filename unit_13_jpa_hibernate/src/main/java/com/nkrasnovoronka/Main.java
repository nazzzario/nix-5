package com.nkrasnovoronka;

import com.nkrasnovoronka.service.ClosestLessonService;
import com.nkrasnovoronka.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                ClosestLessonService closestLessonService = new ClosestLessonService();
                closestLessonService.printInformationAboutClosestLesson(session, 1L);
            }catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }

        }
    }
}
