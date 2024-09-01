package com.emazon.stock_microservice.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity     // @Entity for jpa
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false,length = 50,unique = true)
        private String name;

        @Column(nullable = false, length = 120)
        private String description;

}
