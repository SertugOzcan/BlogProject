package com.example.blogproject.service;

import com.example.blogproject.dto.CategoryDto;
import com.example.blogproject.entity.Category;
import com.example.blogproject.mapper.CategoryMapper;
import com.example.blogproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    public CategoryDto getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.map(categoryMapper::toDto).orElse(null);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category categoryToCreate = categoryMapper.toEntity(categoryDto);
        Category createdCategory = categoryRepository.save(categoryToCreate);
        return categoryMapper.toDto(createdCategory);
    }

    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            categoryRepository.save(existingCategory);
            return categoryMapper.toDto(existingCategory);
        } else {
            return null;
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
