package com.example.quadraticequation;

import com.example.quadraticequation.model.EquationRequest;
import com.example.quadraticequation.model.EquationResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EquationServiceTest {

    @Mock
    private EquationResultRepository equationResultRepository;

    @InjectMocks
    private EquationService equationService;

    @Test
    public void testSolveEquationWithRealRoots() {
        double expectedRootX1 = 1.0;
        double expectedRootX2 = 2.0;
        EquationRequest request = new EquationRequest(1.0, -3.0, 2.0);

        when(equationResultRepository.save(any(EquationResult.class))).thenReturn(null);

        Map<String, Double> result = equationService.solveEquation(request.getA(), request.getB(), request.getC());

        assertEquals(expectedRootX2, result.get("x1"));
        assertEquals(expectedRootX1, result.get("x2"));
    }

    @Test
    public void testSolveEquationWithZeroDiscriminant() {
        EquationRequest request = new EquationRequest(1.0, -2.0, 1.0);

        Mockito.verify(equationResultRepository, Mockito.never()).save(any(EquationResult.class));

        when(equationResultRepository.save(any(EquationResult.class))).thenReturn(null);

        Map<String, Double> result = equationService.solveEquation(request.getA(), request.getB(), request.getC());

        assertEquals(1.0, result.get("x1"));
        assertEquals(1.0, result.get("x2"));
    }

    @Test
    public void testSolveEquationWithNoRealRoots() {
        EquationRequest request = new EquationRequest(1.0, 2.0, 5.0);

        assertThrows(IllegalArgumentException.class, () -> equationService.solveEquation(request.getA(), request.getB(), request.getC()));
    }

    @Test
    public void testSaveEquationResultToDatabase() {
        double expectedRootX1 = 1.0;
        double expectedRootX2 = 2.0;
        EquationRequest request = new EquationRequest(1.0, -3.0, 2.0);

        when(equationResultRepository.save(any(EquationResult.class))).thenReturn(new EquationResult());

        Map<String, Double> result = equationService.solveEquation(request.getA(), request.getB(), request.getC());

        Mockito.verify(equationResultRepository, Mockito.times(1)).save(any(EquationResult.class));

        assertEquals(expectedRootX2, result.get("x1"));
        assertEquals(expectedRootX1, result.get("x2"));
    }
}

