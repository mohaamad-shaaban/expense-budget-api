package com.expense.expense_budget_api.controller;

import com.expense.expense_budget_api.dto.LoginRequest;
import com.expense.expense_budget_api.dto.RegisterDto;
import com.expense.expense_budget_api.dto.UserDto;
import com.expense.expense_budget_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}