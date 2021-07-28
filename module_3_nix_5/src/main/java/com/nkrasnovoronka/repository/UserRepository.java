package com.nkrasnovoronka.repository;

import com.nkrasnovoronka.entity.User;

public interface UserRepository {
    User getUserById(Long userId);
}
