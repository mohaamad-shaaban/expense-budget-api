package com.expense.expense_budget_api.controller;

import com.expense.expense_budget_api.dto.AuthResponse;
import com.expense.expense_budget_api.dto.LoginRequest;
import com.expense.expense_budget_api.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtils jwtUtils;

    public AuthController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = jwtUtils.generateToken(request.getEmail());


        return ResponseEntity.ok(new AuthResponse(token, null));
    }
}