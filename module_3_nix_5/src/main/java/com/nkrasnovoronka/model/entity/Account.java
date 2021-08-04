package com.nkrasnovoronka.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public Account() {
        transactions = new ArrayList<>();
    }

    @PrePersist
    public void onCreate() {
        if (balance == null) {
            balance = 0L;
        }
    }

    public void addTransactionToAccount(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }
}

