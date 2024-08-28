package com.emazon.stock_microservice.infrastructure.output.jpa.repository;


import com.emazon.stock_microservice.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByName(String name);

}
