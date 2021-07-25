package com.nkrasnovoronka.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private Course course;

    @OneToMany(mappedBy = "group")
    @ToString.Exclude
    private Set<Student> students;

    @OneToMany(mappedBy = "group")
    @ToString.Exclude
    private Set<Lesson> lessons;


    public Group() {
        students = new HashSet<>();
        lessons = new HashSet<>();
    }

    public void addStudentToGroup(Student student) {
        students.add(student);
        student.setGroup(this);
    }

    public void addLessonToGroup(Lesson lesson) {
        lessons.add(lesson);
        lesson.setGroup(this);
    }
}
