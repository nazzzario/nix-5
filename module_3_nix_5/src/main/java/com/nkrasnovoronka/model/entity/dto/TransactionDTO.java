package com.nkrasnovoronka.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Instant date;
    private Long value;
    private String categoryType;
    private String accountName;

    public String[] toStringArray() {
        String[] arr = new String[5];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(Locale.FRANCE).withZone(ZoneId.of("UTC"));
        arr[0] = id.toString();
        arr[1] = dateTimeFormatter.format(date);
        arr[2] = value.toString();
        arr[3] = categoryType;
        arr[4] = accountName;
        return arr;
    }
}
