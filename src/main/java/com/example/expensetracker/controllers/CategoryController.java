package com.example.expensetracker.controllers;

import com.example.expensetracker.models.Category;
import com.example.expensetracker.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest httpServletRequest) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        List<Category> categories = categoryService.fetchAllCategories(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId) {
        int userId = (int) httpServletRequest.getAttribute("user_id");
        Category category = categoryService.fetchCategoryById(userId, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> categoryMap) {
        int userId = (int) httpServletRequest.getAttribute("user_id");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");

        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest httpServletRequest, @PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        categoryService.updateCategory(userId, categoryId, category);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
