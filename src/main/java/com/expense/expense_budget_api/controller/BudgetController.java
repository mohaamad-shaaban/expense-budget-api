package com.expense.expense_budget_api.controller;

import com.expense.expense_budget_api.dto.BudgetDto;
import com.expense.expense_budget_api.service.BudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getBudgets(
            @RequestParam Long userId,
            @RequestParam Integer month,
            @RequestParam Integer year) {
        return ResponseEntity.ok(budgetService.getBudgets(userId, month, year));
    }

    @PostMapping
    public ResponseEntity<BudgetDto> createOrUpdateBudget(@RequestParam Long userId, @Valid @RequestBody BudgetDto dto) {
        return ResponseEntity.ok(budgetService.createOrUpdateBudget(userId, dto));
    }
}