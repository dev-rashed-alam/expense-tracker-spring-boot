package com.example.expensetracker.controllers;

import com.example.expensetracker.models.Transaction;
import com.example.expensetracker.services.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId, @RequestBody Map<String, Object> transactionMap) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        double amount = Double.parseDouble(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = (Long) transactionMap.get("transactionDate");

        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }
}
