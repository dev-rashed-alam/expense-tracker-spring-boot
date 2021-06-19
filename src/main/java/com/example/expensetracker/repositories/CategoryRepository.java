package com.example.expensetracker.repositories;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(int userId) throws EtResourceNotFoundException;

    Category findById(int userId, int categoryId) throws EtResourceNotFoundException;

    Integer create(int userId, String title, String description) throws EtBadRequestException;

    void update(int userId, int categoryId, Category category) throws EtBadRequestException;

    void removeById(int userId, int categoryId) throws EtResourceNotFoundException;
}
