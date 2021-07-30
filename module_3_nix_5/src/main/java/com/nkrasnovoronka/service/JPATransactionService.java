package com.nkrasnovoronka.service;

import com.nkrasnovoronka.model.entity.*;
import com.nkrasnovoronka.repository.CategoryRepository;
import com.nkrasnovoronka.repository.TransactionRepository;
import com.nkrasnovoronka.repository.UserRepository;
import com.nkrasnovoronka.repository.impl.CategoryRepositoryImpl;
import com.nkrasnovoronka.repository.impl.TransactionRepositoryImpl;
import com.nkrasnovoronka.repository.impl.UserRepositoryImpl;
import org.hibernate.Session;

import java.util.List;

public class JPATransactionService {

    private final Session session;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public JPATransactionService(Session session) {
        this.session = session;
        this.transactionRepository = new TransactionRepositoryImpl(session);
        this.userRepository = new UserRepositoryImpl(session);
        this.categoryRepository = new CategoryRepositoryImpl(session);
    }

    public void addTransaction(Account account, Transaction transaction, Long value, Long categoryId) {
        Category category = session.find(Category.class, categoryId);
        transaction.setValue(value);
        transaction.setCategory(category);
        account.setBalance(account.getBalance() + transaction.getValue());
        account.addTransactionToAccount(transaction);
        session.update(account);
        session.flush();
        session.save(transaction);
    }

    public List<IncomeCategory> getIncomeCategories() {
        return categoryRepository.getIncomeCategories();
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return categoryRepository.getExpenseCategories();
    }

    public User findUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<Account> findAccountsOfUser(Long id) {
        return userRepository.getAccountsOfUser(id);
    }

}
