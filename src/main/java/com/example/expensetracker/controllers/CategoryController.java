package com.example.expensetracker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public String getAllCategories(HttpServletRequest httpServletRequest){
        int userId = (Integer) httpServletRequest.getAttribute("user_id");
        return "Authenticated! UserId: " + userId;
    }
}
