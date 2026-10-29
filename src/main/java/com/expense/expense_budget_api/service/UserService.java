package com.expense.expense_budget_api.service;

import com.expense.expense_budget_api.dto.LoginRequest;
import com.expense.expense_budget_api.dto.RegisterDto;
import com.expense.expense_budget_api.dto.UserDto;
import com.expense.expense_budget_api.entity.User;
import com.expense.expense_budget_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto register(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .email(dto.getEmail())
                .passwordHash(dto.getPassword())
                .build();
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    public UserDto login(LoginRequest dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!user.getPasswordHash().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}