package com.example.expensetracker.services.impl;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;
import com.example.expensetracker.services.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User validUser(String emil, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }
}

