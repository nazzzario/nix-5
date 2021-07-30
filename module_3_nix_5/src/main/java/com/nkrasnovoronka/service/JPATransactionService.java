package com.nkrasnovoronka.service;

import com.nkrasnovoronka.repository.CategoryRepository;
import com.nkrasnovoronka.repository.TransactionRepository;
import com.nkrasnovoronka.repository.UserRepository;
import com.nkrasnovoronka.repository.impl.CategoryRepositoryImpl;
import com.nkrasnovoronka.repository.impl.TransactionRepositoryImpl;
import com.nkrasnovoronka.model.entity.*;
import com.nkrasnovoronka.repository.impl.UserRepositoryImpl;
import org.hibernate.Session;


import java.util.List;

public class JPATransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public JPATransactionService(Session session) {
        this.transactionRepository = new TransactionRepositoryImpl(session);
        this.userRepository = new UserRepositoryImpl(session);
        this.categoryRepository = new CategoryRepositoryImpl(session);
    }

    public void save(Transaction transaction) {
        if (transaction.getCategory() == null)
            throw new RuntimeException("No category presented in transaction");
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<IncomeCategory> getIncomeCategories()  {
        return categoryRepository.getIncomeCategories();
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return categoryRepository.getExpenseCategories();
    }

    public User findUserByEmail(String email)  {
        return userRepository.getUserByEmail(email);
    }

    public List<Account> findAccountsOfUser(Long id) {
        return userRepository.getAccountsOfUser(id);
    }

    public IncomeCategory findIncomeCategoriesByListOfIds(Long id) {
        return categoryRepository.getIncomeCategoryId(id);
    }

    public ExpenseCategory findExpenseCategoriesByListOfIds(Long id) {
        return categoryRepository.getExpenseCategoryById(id);
    }
}
