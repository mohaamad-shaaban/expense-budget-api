package com.expense.expense_budget_api.service;

import com.expense.expense_budget_api.dto.BudgetDto;
import com.expense.expense_budget_api.entity.Budget;
import com.expense.expense_budget_api.entity.Category;
import com.expense.expense_budget_api.entity.User;
import com.expense.expense_budget_api.repository.BudgetRepository;
import com.expense.expense_budget_api.repository.CategoryRepository;
import com.expense.expense_budget_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<BudgetDto> getBudgets(Long userId, Integer month, Integer year) {
        return budgetRepository.findAllByUserIdAndMonthAndYear(userId, month, year).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BudgetDto createOrUpdateBudget(Long userId, BudgetDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findByIdAndUserId(dto.getCategoryId(), userId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Budget budget = budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(
                userId, dto.getCategoryId(), dto.getMonth(), dto.getYear()
        ).orElse(new Budget());

        budget.setUser(user);
        budget.setCategory(category);
        budget.setAmount(dto.getAmount());
        budget.setMonth(dto.getMonth());
        budget.setYear(dto.getYear());

        Budget saved = budgetRepository.save(budget);
        return mapToDto(saved);
    }

    private BudgetDto mapToDto(Budget budget) {
        BudgetDto dto = new BudgetDto();
        dto.setId(budget.getId());
        dto.setAmount(budget.getAmount());
        dto.setMonth(budget.getMonth());
        dto.setYear(budget.getYear());
        if (budget.getCategory() != null) {
            dto.setCategoryId(budget.getCategory().getId());
            dto.setCategoryName(budget.getCategory().getName());
        }
        return dto;
    }
}