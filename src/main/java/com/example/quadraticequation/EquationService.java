package com.example.quadraticequation;

import com.example.quadraticequation.model.EquationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EquationService {
    private final EquationResultRepository equationResultRepository;

    public Map<String, Double> solveEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant >= 0) {
            double sqrtDiscriminant = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDiscriminant) / (2 * a);
            double x2 = (-b - sqrtDiscriminant) / (2 * a);

            EquationResult equationResult = new EquationResult(a, b, c, x1, x2);
            equationResultRepository.save(equationResult);

            Map<String, Double> roots = new HashMap<>();
            roots.put("x1", x1);
            roots.put("x2", x2);

            return roots;
        } else {
            throw new IllegalArgumentException("Уравнение не имеет действительных корней");
        }
    }
}



