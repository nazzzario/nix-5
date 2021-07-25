package com.nkrasnovoronka.model.entity;

import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", unique = true)
    private String topicName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private Set<Lesson> lessons;

    public Topic() {
        lessons = new HashSet<>();
    }

    public void addLessonToTopic(Lesson lesson){
        lessons.add(lesson);
        lesson.setTopic(this);
    }
}
