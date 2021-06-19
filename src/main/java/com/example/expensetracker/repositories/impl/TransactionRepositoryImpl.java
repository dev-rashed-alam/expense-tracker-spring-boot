package com.example.expensetracker.repositories.impl;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Transaction;
import com.example.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private static final String SQL_CREATE = "insert into et_transactions (category_id,user_id,amount,note,transaction_date) values (?,?,?,?,?)";

    private static final String SQL_FIND_BY_ID = "select * from et_transactions where user_id = ? and category_id = ? and transaction_id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> findAll(int userId, int categoryId) {
        return null;
    }

    @Override
    public Transaction findById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, transactionRowMapper, userId, categoryId, transactionId);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Transaction not found!");
        }
    }

    @Override
    public Integer create(int userId, int categoryId, double amount, String note, Long transactionDate) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, categoryId);
                ps.setInt(2, userId);
                ps.setDouble(3, amount);
                ps.setString(4, note);
                ps.setLong(5, transactionDate);
                return ps;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).intValue();
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Transaction Details!");
        }
    }

    @Override
    public void update(int userId, int categoryId, int transactionId, Transaction transaction) throws EtBadRequestException {

    }

    @Override
    public void removeById(int userId, int categoryId, int transactionId) throws EtResourceNotFoundException {

    }

    private RowMapper<Transaction> transactionRowMapper = ((res, rowNum) -> new Transaction(
            res.getInt("category_id"),
            res.getInt("user_id"),
            res.getDouble("amount"),
            res.getString("note"),
            res.getLong("transaction_date")
    ));
}
