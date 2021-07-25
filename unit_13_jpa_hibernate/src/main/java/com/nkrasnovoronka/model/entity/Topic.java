package com.nkrasnovoronka.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "topics")
@Entity
@Getter
@Setter
@ToString
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "teacher_id")
    @ToString.Exclude
    private Teacher teacher;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Lesson> lessons;

    public Topic() {
        lessons = new HashSet<>();
    }
}
