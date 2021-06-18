package com.example.expensetracker.services.impl;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.models.User;
import com.example.expensetracker.repositories.impl.UserRepositoryImpl;
import com.example.expensetracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    @Override
    public User validUser(String email, String password) throws EtAuthException {
        if (email != null)
            email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) email = email.toLowerCase();
        if (!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid Email Format!");
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw new EtAuthException("Email Already in use!");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        System.out.println(userId);
        return userRepository.findById(userId);
    }
}

