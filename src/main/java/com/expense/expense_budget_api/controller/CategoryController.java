package com.expense.expense_budget_api.controller;

import com.expense.expense_budget_api.dto.CategoryDto;
import com.expense.expense_budget_api.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(@RequestParam Long userId) {
        return ResponseEntity.ok(categoryService.getAllCategories(userId));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestParam Long userId, @Valid @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.createCategory(userId, dto));
    }
}