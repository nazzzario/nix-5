package com.nkrasnovoronka.dao;

import com.nkrasnovoronka.model.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class StudentDAO {
    public Student getStudentById(Session session, Long studentId){
        Query query = session.createQuery("select s from Student s where s.id = :studentId");
        query.setParameter("studentId", studentId);
        return (Student) query.getSingleResult();
    }
}
