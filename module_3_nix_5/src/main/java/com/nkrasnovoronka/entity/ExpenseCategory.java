package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("expense")
@Getter
@Setter
public class ExpenseCategory extends Category{
    @Column(name = "description")
    private String description;
}
