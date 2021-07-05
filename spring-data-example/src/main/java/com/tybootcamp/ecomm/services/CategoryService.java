package com.tybootcamp.ecomm.services;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.exceptions.BadRequest;
import com.tybootcamp.ecomm.exceptions.EntityNotFound;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addNewCategory(String name) {
        if (Strings.isBlank(name)) {
            throw new BadRequest();
        }
        Category category = new Category(name.trim());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category updatedCategory) {
        if (updatedCategory == null || Strings.isBlank(updatedCategory.getName()))
            throw new BadRequest();

        Category category = categoryRepository.findById(updatedCategory.getId()).orElseThrow(EntityNotFound::new);
        category.setName(updatedCategory.getName());
        return categoryRepository.save(category);
    }

    public Category getCategoryByName(String name) throws EntityNotFound {
        if (Strings.isBlank(name)) {
            throw new BadRequest();
        }
        return categoryRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }
}
