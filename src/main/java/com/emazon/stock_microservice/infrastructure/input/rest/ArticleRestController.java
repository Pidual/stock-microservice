package com.emazon.stock_microservice.infrastructure.input.rest;

import com.emazon.stock_microservice.application.dto.ArticleDTO;
import com.emazon.stock_microservice.application.dto.ArticleStockRequestDTO;
import com.emazon.stock_microservice.application.handler.IArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@Validated

public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @GetMapping("/")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleHandler.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve a paginated list of articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved a page of articles",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/paged")
    public ResponseEntity<Page<ArticleDTO>> getArticlesPaged(Pageable pageable) {
        Page<ArticleDTO> articles = articleHandler.getAllArticlesPaged(pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }



    @Operation(summary = "Add a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Void> addArticle(@RequestBody @Valid ArticleDTO articleDTO){
        articleHandler.saveArticle(articleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    


    @PreAuthorize("hasAnyRole('AUX_BODEGA')")
    @PatchMapping("/add-stock")
    public ResponseEntity<Void> addArticleStock(@RequestBody @Valid ArticleStockRequestDTO articleStockRequestDTO) {
        articleHandler.addStockToArticle(articleStockRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
