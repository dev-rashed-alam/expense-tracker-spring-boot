package com.example.expensetracker.services;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> fetchAllTransactions(int userId, int categoryId);

    Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;

    Transaction addTransaction(int userId, int categoryId, double amount, String note, Long transactionDate) throws EtBadRequestException;

    void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException;

    void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException;

}
