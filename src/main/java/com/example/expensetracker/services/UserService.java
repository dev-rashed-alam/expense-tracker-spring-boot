package com.example.expensetracker.services;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;

public interface UserService {

    User validUser(String emil, String password) throws EtAuthException;

    User registerUser(String firstName,String lastName, String email, String password) throws EtAuthException;
}
