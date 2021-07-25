package com.nkrasnovoronka.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "teachers")
@Entity
@Getter
@Setter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    private Set<Topic> topics;

    public Teacher() {
        topics = new HashSet<>();
    }

    public void addTopicToTeacher(Topic topic){
        topics.add(topic);
        topic.setTeacher(this);
    }
}
