package com.nkrasnovoronka.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category_type", discriminatorType = DiscriminatorType.STRING)
public class Category extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transaction;
}
