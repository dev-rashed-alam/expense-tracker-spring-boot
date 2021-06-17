package com.example.expensetracker.repositories.impl;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;
import com.example.expensetracker.repositories.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
