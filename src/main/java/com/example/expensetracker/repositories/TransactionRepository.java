package com.example.expensetracker.repositories;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(int userId, int categoryId);

    Transaction findById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;

    Integer create(int userId, int categoryId, double amount, String note, Long transactionDate) throws EtBadRequestException;

    void update(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException;

    void removeById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;
}
