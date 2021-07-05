package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategoryByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PostMapping
    public ResponseEntity<Category> addNewCategory(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(categoryService.addNewCategory(name));
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }
}
