package com.emazon.stock_microservice.infrastructure.output.jpa.repository;



import com.emazon.stock_microservice.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    ArticleEntity findByName(String articleName);



}
