package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByName(String name);

    Optional<Category> findByName(String name);
}
