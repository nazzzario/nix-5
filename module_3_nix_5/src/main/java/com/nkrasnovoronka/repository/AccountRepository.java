package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.entity.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> getAllByUserId(Long userId);
}
