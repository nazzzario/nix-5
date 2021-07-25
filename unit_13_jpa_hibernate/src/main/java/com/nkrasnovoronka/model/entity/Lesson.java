package com.nkrasnovoronka.model.entity;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_time", nullable = false)
    private LocalDate dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "lesson")
    private Set<Mark> marks;

    public Lesson() {
        marks = new HashSet<>();
    }

    public void addMarkToLesson(Mark mark){
        marks.add(mark);
        mark.setLesson(this);
    }
}
