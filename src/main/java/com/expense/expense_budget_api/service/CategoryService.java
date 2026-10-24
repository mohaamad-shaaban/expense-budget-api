package com.expense.expense_budget_api.service;

import com.expense.expense_budget_api.dto.CategoryDto;
import com.expense.expense_budget_api.entity.Category;
import com.expense.expense_budget_api.entity.User;
import com.expense.expense_budget_api.repository.CategoryRepository;
import com.expense.expense_budget_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<CategoryDto> getAllCategories(Long userId) {
        return categoryRepository.findAllByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(Long userId, CategoryDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (categoryRepository.existsByUserIdAndName(userId, dto.getName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = Category.builder()
                .name(dto.getName())
                .type(dto.getType())
                .user(user)
                .build();

        Category saved = categoryRepository.save(category);
        return mapToDto(saved);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setType(category.getType());
        return dto;
    }
}