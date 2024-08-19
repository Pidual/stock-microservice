package com.emazon.stock_microservice.infraestructure.output.jpa.repository;

import com.emazon.stock_microservice.domain.model.Category;
import com.emazon.stock_microservice.infraestructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Search why do we use jpa
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    // deleteById is already provided by JpaRepository, no need to declare it again

}
