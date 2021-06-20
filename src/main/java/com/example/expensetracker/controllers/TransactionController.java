package com.example.expensetracker.controllers;

import com.example.expensetracker.models.Transaction;
import com.example.expensetracker.services.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId, @PathVariable("transactionId") int transactionId) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransactionById(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId, @PathVariable("transactionId") int transactionId, @RequestBody Transaction transactionMap) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        transactionService.updateTransaction(userId, categoryId, transactionId, transactionMap);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransactionById(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId, @PathVariable("transactionId") int transactionId) {
        int userId = (int) httpServletRequest.getAttribute("user_id");
        transactionService.removeTransaction(userId, categoryId, transactionId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
