package com.emazon.stock_microservice.infrastructure.output.jpa.repository;

import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import lombok.NonNull;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByName(String name);

    @NonNull // If you're sure that pageable is never null you can use this annotation
    Page<BrandEntity> findAll(@NonNull Pageable pageable); // Pageable for better use of the database


}
