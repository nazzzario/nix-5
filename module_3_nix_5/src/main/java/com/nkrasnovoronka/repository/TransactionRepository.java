package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.model.entity.Transaction;


public interface TransactionRepository {
    void save(Transaction transaction);
}
