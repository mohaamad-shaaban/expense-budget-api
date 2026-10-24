package com.expense.expense_budget_api.service;

import com.expense.expense_budget_api.dto.ExpenseDto;
import com.expense.expense_budget_api.entity.Category;
import com.expense.expense_budget_api.entity.Expense;
import com.expense.expense_budget_api.entity.User;
import com.expense.expense_budget_api.repository.CategoryRepository;
import com.expense.expense_budget_api.repository.ExpenseRepository;
import com.expense.expense_budget_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<ExpenseDto> getFilteredExpenses(Long userId, Long categoryId, LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findFilteredExpenses(userId, categoryId, startDate, endDate).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ExpenseDto createExpense(Long userId, ExpenseDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findByIdAndUserId(dto.getCategoryId(), userId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        Expense expense = Expense.builder()
                .user(user)
                .category(category)
                .amount(dto.getAmount())
                .date(dto.getDate())
                .description(dto.getDescription())
                .build();

        Expense saved = expenseRepository.save(expense);
        return mapToDto(saved);
    }

    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setDescription(expense.getDescription());
        if (expense.getCategory() != null) {
            dto.setCategoryId(expense.getCategory().getId());
            dto.setCategoryName(expense.getCategory().getName());
        }
        return dto;
    }
}