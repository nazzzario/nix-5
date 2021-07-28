package com.nkrasnovoronka.service;

import com.nkrasnovoronka.dao.LessonDAO;
import com.nkrasnovoronka.dao.StudentDAO;
import com.nkrasnovoronka.model.entity.Lesson;
import com.nkrasnovoronka.model.entity.Student;
import com.nkrasnovoronka.model.entity.Teacher;
import com.nkrasnovoronka.model.entity.Topic;
import org.hibernate.Session;

public class ClosestLessonService {
    public LessonDAO lessonDAO = new LessonDAO();
    public StudentDAO studentDAO = new StudentDAO();
    public void printInformationAboutClosestLesson(Session session, Long studentId){
        Student studentById = studentDAO.getStudentById(session, studentId);
        Lesson closestLesson = lessonDAO.getClosestLessonByStudentId(session, studentId);
        Topic topic = closestLesson.getTopic();
        Teacher teacher = closestLesson.getTopic().getTeacher();
        System.out.printf("Student %s %s", studentById.getFirstName(), studentById.getLastName());
        System.out.printf("Closest lesson is %s starts at %s", closestLesson.getName(), closestLesson.getDateTime());
        System.out.printf("Teacher: %s %s, Topic: %s", teacher.getFirstName(), teacher.getLastName(), topic.getTopicName());
    }
}
