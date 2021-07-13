package com.nkrasnovoronka.model;

import com.nkrasnovoronka.annotation.CsvMapper;

public class User {
    @CsvMapper("id")
    private int id;
    @CsvMapper("firstName")
    private String firstName;
    @CsvMapper("lastName")
    private String lastName;
    @CsvMapper("role")
    private Role role;
    @CsvMapper("active")
    private boolean active;
    @CsvMapper("age")
    private int age;
}
