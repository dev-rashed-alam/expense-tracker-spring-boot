package com.example.expensetracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private int categoryId;
    private int userId;
    private String title;
    private String description;
    private double totalExpense;
}
