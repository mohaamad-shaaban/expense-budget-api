package com.expense.expense_budget_api.controller;

import com.expense.expense_budget_api.dto.ExpenseDto;
import com.expense.expense_budget_api.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getFilteredExpenses(
            @RequestParam Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(expenseService.getFilteredExpenses(userId, categoryId, startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestParam Long userId, @Valid @RequestBody ExpenseDto dto) {
        return ResponseEntity.ok(expenseService.createExpense(userId, dto));
    }
}