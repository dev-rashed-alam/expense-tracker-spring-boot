package com.example.expensetracker.controllers;

import com.example.expensetracker.models.Category;
import com.example.expensetracker.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping
    public String getAllCategories(HttpServletRequest httpServletRequest) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        return "Authenticated! UserId: " + userId;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> categoryMap) {
        int userId = (int) httpServletRequest.getAttribute("user_id");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");

        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
}
