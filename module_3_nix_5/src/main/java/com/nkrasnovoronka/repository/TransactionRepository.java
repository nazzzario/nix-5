package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.entity.Account;
import com.nkrasnovoronka.entity.Transaction;

public interface TransactionRepository {
    void addTransaction(Account account, Transaction transaction);


}
