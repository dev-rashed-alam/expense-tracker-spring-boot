package com.example.expensetracker.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
