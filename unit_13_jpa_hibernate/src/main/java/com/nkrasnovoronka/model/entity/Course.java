package com.nkrasnovoronka.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "corses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
    private Set<Group> groups;

    @OneToMany(mappedBy = "course")
    private Set<Topic> topics;

    @OneToMany(mappedBy = "course")
    private Set<Teacher> teachers;

    public Course() {
        groups = new HashSet<>();
        topics = new HashSet<>();
        teachers = new HashSet<>();
    }

    public void addGroupToCourse(Group group){
        groups.add(group);
        group.setCourse(this);
    }

    public void addTopicToCourse(Topic topic){
        topics.add(topic);
        topic.setCourse(this);
    }

    public void addTeacherToCourse(Teacher teacher){
        teachers.add(teacher);
        teacher.setCourse(this);
    }
}
