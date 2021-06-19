package com.example.expensetracker.services.impl;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.models.Category;
import com.example.expensetracker.repositories.impl.CategoryRepositoryImpl;
import com.example.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepositoryImpl categoryRepository;

    @Override
    public List<Category> fetchAllCategories(int userId) {
        return null;
    }

    @Override
    public Category fetchCategoryById(int userId, int categoryId) throws EtResourceNotFoundException {
        return null;
    }

    @Override
    public Category addCategory(int userId, String title, String description) throws EtBadRequestException {
        int categoryId = categoryRepository.create(userId, title, description);
        System.out.println(categoryId);
        return categoryRepository.findById(userId, categoryId);
    }

    @Override
    public void updateCategory(int userId, int categoryId, Category category) throws EtBadRequestException {

    }

    @Override
    public void removeAllCategoryWithAllTransaction(int userId, int categoryId) throws EtResourceNotFoundException {

    }
}
