package com.example.pal.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pal.dto.CategoryDTO;
import com.example.pal.model.Category;
import com.example.pal.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    @Autowired private ModelMapper modelMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
    
}
