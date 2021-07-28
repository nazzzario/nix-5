package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.entity.Transaction;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
}
