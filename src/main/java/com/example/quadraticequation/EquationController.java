package com.example.quadraticequation;

import com.example.quadraticequation.model.EquationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/equation")
@RequiredArgsConstructor
public class EquationController {
    private final EquationService equationService;

    @PostMapping("/solve")
    public ResponseEntity<Map<String, Double>> solveEquation(@RequestBody EquationRequest request) {
        Map<String, Double> result = equationService.solveEquation(request.getA(), request.getB(), request.getC());
        return ResponseEntity.ok(result);
    }
}
