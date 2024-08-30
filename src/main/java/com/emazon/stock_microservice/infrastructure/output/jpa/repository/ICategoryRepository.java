package com.emazon.stock_microservice.infrastructure.output.jpa.repository;

import com.emazon.stock_microservice.infrastructure.output.jpa.entity.CategoryEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByName(String name);

    @NonNull // If you're sure that pageable is never null you can use this annotation
    Page<CategoryEntity> findAll(@NonNull Pageable pageable); // Pageable for better use of the database
}
