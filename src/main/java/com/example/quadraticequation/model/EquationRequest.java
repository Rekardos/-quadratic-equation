package com.example.quadraticequation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquationRequest {
    private double a;
    private double b;
    private double c;
}

