package com.example.expensetracker.services.impl;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Transaction;
import com.example.expensetracker.repositories.impl.TransactionRepositoryImpl;
import com.example.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepositoryImpl transactionRepository;

    @Override
    public List<Transaction> fetchAllTransactions(int userId, int categoryId) {
        return null;
    }

    @Override
    public Transaction fetchTransactionById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    @Override
    public Transaction addTransaction(int userId, int categoryId, double amount, String note, Long transactionDate) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId, categoryId, amount, note, transactionDate);
        return transactionRepository.findById(userId, categoryId, transactionId);

    }

    @Override
    public void updateTransaction(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeTransaction(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {

    }
}
