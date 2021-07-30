package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.model.entity.Account;
import com.nkrasnovoronka.model.entity.User;

import java.util.List;

public interface UserRepository {
    User getUserByEmail(String email);

    List<Account> getAccountsOfUser(Long id);
}
