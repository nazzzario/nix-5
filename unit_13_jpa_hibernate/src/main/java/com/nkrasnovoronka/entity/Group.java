package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", unique = true)
    private String groupName;

    @OneToMany(mappedBy = "group")
    private Set<Student> students;

    public Group() {
        students = new HashSet<>();
    }

    public void addStudentToGroup(Student student){
        students.add(student);
        student.setGroup(this);
    }

}
