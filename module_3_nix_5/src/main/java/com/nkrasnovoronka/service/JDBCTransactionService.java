package com.nkrasnovoronka.service;

import com.nkrasnovoronka.model.entity.Account;
import com.nkrasnovoronka.model.entity.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JDBCTransactionService {
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    public static final String GET_TRANSACTIONS = "select t.id, t.date, t.value, c.category_type, a.account_name " +
            "from transactions t " +
            "join categories c on t.category_id = c.id " +
            "join accounts a on a.id = t.account_id " +
            "where a.id = ? and " +
            "t.date between ? and ? " +
            "order by c.category_type, t.value";

    public static final String GET_ACCOUNTS = "select a.id,a.account_name,a.balance from public.accounts a " +
            "join public.users on public.users.id=a.user_id " +
            "where public.users.email = ?";
    private final Connection connection;

    public JDBCTransactionService(Connection connection) {
        this.connection = connection;
    }

    public List<TransactionDTO> getTransactions(Long accountId, Timestamp from, Timestamp to) {
        loggerInfo.info("Getting all transactions by {}", accountId);
        try (PreparedStatement getValue = connection.prepareStatement(GET_TRANSACTIONS)) {
            getValue.setLong(1, accountId);
            getValue.setTimestamp(2, from);
            getValue.setTimestamp(3, to);
            ResultSet resultSet = getValue.executeQuery();

            List<TransactionDTO> transactions = new ArrayList<>();
            while (resultSet.next()) {
                TransactionDTO transaction = new TransactionDTO(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("date").toInstant(),
                        resultSet.getLong("value"),
                        resultSet.getString("category_type"),
                        resultSet.getString("account_name")
                );
                transactions.add(transaction);
            }
            return transactions;
        } catch (Exception e) {
            loggerError.error("Cannot get transactions from db", e);
            throw new RuntimeException("Transactions cann`t be exported", e);
        }
    }


    public List<Account> getAccountsByUserEmail(String userEmail) {
        loggerInfo.info("Getting user by email {}", userEmail);
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement getValue = connection.prepareStatement(GET_ACCOUNTS)) {
            getValue.setString(1, userEmail);
            ResultSet resultSet = getValue.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setAccountName(resultSet.getString("account_name"));
                account.setBalance(resultSet.getLong("balance"));
                accounts.add(account);
            }
            return accounts;
        } catch (Exception e) {
            loggerError.error("Cannot get user with email {}", userEmail);
            throw new RuntimeException(e);
        }
    }
}
