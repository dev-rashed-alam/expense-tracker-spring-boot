package com.example.expensetracker.services;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> fetchAllCategories(int userId);

    Category fetchCategoryById(int userId, int categoryId) throws EtResourceNotFoundException;

    Category addCategory(int userId, String title, String description) throws EtBadRequestException;

    void updateCategory(int userId, int categoryId, Category category) throws EtBadRequestException;

    void removeAllCategoryWithAllTransaction(int userId, int categoryId) throws EtResourceNotFoundException;
}
