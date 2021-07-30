package com.nkrasnovoronka.model.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransactionDTO {
    private Long id;
    private Instant passedAt;
    private List<String> categories = new ArrayList<>();
    private Long transaction;


    public TransactionDTO(Long id, Instant passedAt, List<String> categories, Long transaction) {
        this.id = id;
        this.passedAt = passedAt;
        this.categories = categories;
        this.transaction = transaction;
    }

    public TransactionDTO(Instant passedAt, Long transaction) {
        this.passedAt = passedAt;
        this.transaction = transaction;
    }
}
