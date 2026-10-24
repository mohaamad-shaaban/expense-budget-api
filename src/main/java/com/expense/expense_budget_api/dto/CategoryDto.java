package com.expense.expense_budget_api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Category name is mandatory")
    private String name;

    private String type;
}
