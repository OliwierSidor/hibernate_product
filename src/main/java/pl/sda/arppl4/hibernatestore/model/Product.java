package pl.sda.arppl4.hibernatestore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

    @Data
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private Double price;
        private String producent;
        private LocalDate expiryDate;
        private Double quantity;

        @Enumerated(EnumType.STRING)
        private ProductUnit unit;
    }

