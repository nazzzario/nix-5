package com.nkrasnovoronka.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "lessons")
@Entity
@Getter
@Setter
@ToString
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    @ToString.Exclude
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @ToString.Exclude
    private Topic topic;

    @OneToMany(mappedBy = "lesson")
    @ToString.Exclude
    private Set<Grade> grades;


    public Lesson() {
        grades = new HashSet<>();
    }

    public void addGrateToLesson(Grade grade){
        grades.add(grade);
        grade.setLesson(this);
    }
}
