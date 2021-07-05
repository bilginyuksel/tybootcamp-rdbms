package com.tybootcamp.ecomm.mocks;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCategoryRepository implements CategoryRepository {
    private final Map<Long, Category> categoryDb = new HashMap<>();
    private final Map<String, Long> categoryDbNameIndex = new HashMap<>();

    @Override
    public Optional<Category> findByName(String name) {
        if (!categoryDbNameIndex.containsKey(name)) {
            return Optional.empty();
        }
        long categoryId = categoryDbNameIndex.get(name);
        return Optional.of(categoryDb.get(categoryId));
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        if (!categoryDb.containsKey(aLong)) {
            return Optional.empty();
        }
        return Optional.of(categoryDb.get(aLong));
    }

    @Override
    public <S extends Category> S save(S s) {
        long categoryId = s.getId();
        String categoryName = s.getName();

        if (categoryDb.containsKey(categoryId)) {
            Category oldCategory = categoryDb.get(categoryId);
            categoryDbNameIndex.remove(oldCategory.getName());
        }

        Category savedCategory = categoryDb.put(categoryId, s);
        categoryDbNameIndex.put(categoryName, categoryId);
        return (S) savedCategory;
    }

    public void clearDB() {
        categoryDb.clear();
        categoryDbNameIndex.clear();
    }

    @Override
    public List<Category> findAllByName(String name) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Category> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Category> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Category> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Category getOne(Long aLong) {
        return null;
    }

    @Override
    public Category getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return false;
    }
}
