package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "category_type")
public class Category extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;

    @Column(name = "description")
    private String description;


}
