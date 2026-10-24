package com.expense.expense_budget_api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String email;
    private LocalDateTime createdAt;
}