package com.example.expensetracker.repositories;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
