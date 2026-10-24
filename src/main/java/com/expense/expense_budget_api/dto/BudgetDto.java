package com.expense.expense_budget_api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class BudgetDto {
    private Long id;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Month is mandatory")
    private Integer month;

    @NotNull(message = "Year is mandatory")
    private Integer year;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
    private String categoryName;
}