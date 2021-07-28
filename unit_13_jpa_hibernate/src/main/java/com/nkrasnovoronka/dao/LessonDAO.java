package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class LessonDAO {

    public Lesson getClosestLessonByStudentId(Session session, Long studentId) {
        Query query = session.createQuery("select l from Lesson l " +
                "join Group g on l.group.id = g.id " +
                "join Student s on s.group.id = g.id " +
                "where s.id = :id " +
                "and l.dateTime > CURRENT_DATE " +
                "order by l.dateTime asc").setMaxResults(1);
        query.setParameter("id", studentId);
        return (Lesson) query.getSingleResult();
    }
}
