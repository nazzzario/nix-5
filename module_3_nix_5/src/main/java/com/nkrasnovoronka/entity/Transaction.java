package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;
import java.time.Instant;
import java.util.Locale;
import java.util.Set;


@Entity
@Table(name = "transactions")
@Getter
@Setter

public class Transaction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false,columnDefinition = "TIMESTAMP")
    private Instant date;

    @Column(name = "tramsaction_amount")
    private Double transactionAmount;


    @OneToMany(mappedBy = "transaction")
    private Set<Category> categories;


    public Transaction() {
        date = Instant.now();
    }

    public Transaction(Instant date, Double transactionAmount) {
        this();
        this.date = date;
        this.transactionAmount = transactionAmount;
    }

    public void addCategoryToTransaction(Category category){
        categories.add(category);
        category.setTransaction(this);
    }
}
