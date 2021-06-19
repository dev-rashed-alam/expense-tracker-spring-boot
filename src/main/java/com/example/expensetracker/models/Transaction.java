package com.example.expensetracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private int categoryId;
    private int userId;
    private double amount;
    private String note;
    private Long transactionDate;
}
