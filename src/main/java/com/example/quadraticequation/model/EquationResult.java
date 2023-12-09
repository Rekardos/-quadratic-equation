package com.example.quadraticequation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EquationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double a;
    private Double b;
    private Double c;
    private Double x1;
    private Double x2;

    public EquationResult(Double a, Double b, Double c, Double x1, Double x2) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.x1 = x1;
        this.x2 = x2;
    }
}

