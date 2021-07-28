package com.nkrasnovoronka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends BaseEntity {
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

    public Account() {
        transactions = new HashSet<>();
    }

    public Account(Double balance, User user) {
        this();
        this.balance = balance;
        this.user = user;
    }

    public void addTransactionToAccount(Transaction transaction){
        transactions.add(transaction);
        transaction.setAccount(this);
    }
}
