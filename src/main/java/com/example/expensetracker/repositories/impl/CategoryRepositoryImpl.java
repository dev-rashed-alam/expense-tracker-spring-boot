package com.example.expensetracker.repositories.impl;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Category;
import com.example.expensetracker.repositories.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String SQL_UPDATE = "update et_categories set title = ?,description = ? where user_id = ? and category_id = ?";

    private static final String SQL_FIND_BY_ID = "select c.user_id,c.category_id,c.title,c.description,coalesce(sum(t.amount),0) total_expense from et_transactions t right outer join et_categories c on c.category_id = t.category_id where c.user_id = ? and c.category_id = ? group by category_id";

    private static final String SQL_FIND_ALL = "select c.user_id,c.category_id,c.title,c.description,coalesce(sum(t.amount),0) total_expense from et_transactions t right outer join et_categories c on c.category_id = t.category_id where c.user_id = ? group by category_id";

    private static final String SQL_CREATE = "insert into et_categories (user_id,title,description) values (?,?,?)";

    private static final String SQL_REMOVE_TRANSACTION = "delete from et_transactions where user_id = ? and category_id = ?";

    private static final String SQL_REMOVE_CATEGORY = "delete from et_categories where user_id =? and category_id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll(int userId) throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, categoryRowMapper, userId);
    }

    @Override
    public Category findById(int userId, int categoryId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, categoryRowMapper, userId, categoryId);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Category not found!");
        }
    }

    @Override
    public Integer create(int userId, String title, String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            return Objects.requireNonNull(keyHolder.getKey()).intValue();
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request");
        }
    }

    @Override
    public void update(int userId, int categoryId, Category category) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, category.getTitle(), category.getDescription(), userId, categoryId);
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Details!");
        }
    }

    @Override
    public void removeById(int userId, int categoryId) throws EtResourceNotFoundException {
        removeAllTransactionByCategory(userId, categoryId);
        jdbcTemplate.update(SQL_REMOVE_CATEGORY, userId, categoryId);
    }

    private void removeAllTransactionByCategory(int userId, int categoryId) {
        jdbcTemplate.update(SQL_REMOVE_TRANSACTION, userId, categoryId);
    }

    public RowMapper<Category> categoryRowMapper = ((res, rowNum) -> new Category(
            res.getInt("category_id"),
            res.getInt("user_id"),
            res.getString("title"),
            res.getString("description"),
            res.getDouble("total_expense")
    ));
}
