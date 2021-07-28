package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "category_type")
@DiscriminatorFormula("case when income not null then 'income' else 'expense' end" )
public class Category extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;


}
