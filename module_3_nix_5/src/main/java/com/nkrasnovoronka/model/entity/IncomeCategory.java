package com.nkrasnovoronka.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("income")
public class IncomeCategory extends Category {
}
