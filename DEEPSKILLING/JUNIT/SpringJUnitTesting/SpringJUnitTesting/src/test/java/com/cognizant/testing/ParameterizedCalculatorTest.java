package com.cognizant.testing;

import com.cognizant.testing.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 9 - Parameterized Test with JUnit.
 * <p>
 * Demonstrates {@code @ParameterizedTest} with both {@code @CsvSource} and
 * {@code @ValueSource} to exercise {@link CalculatorService#add(int, int)}
 * against multiple sets of inputs.
 */
@DisplayName("CalculatorService Parameterized Tests")
class ParameterizedCalculatorTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    /**
     * Verifies add() against several (a, b, expected) triples supplied via CSV.
     */
    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "0, 0, 0",
            "-1, 1, 0",
            "-5, -5, -10",
            "100, 200, 300"
    })
    @DisplayName("add() should return the correct sum for various CSV-sourced inputs")
    void testAdd_WithCsvSource(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }

    /**
     * Verifies that adding zero to a variety of single values returns the
     * original value unchanged.
     */
    @ParameterizedTest(name = "add({0}, 0) = {0}")
    @ValueSource(ints = {1, 5, 10, -3, 0, 999})
    @DisplayName("add() should be a no-op when adding zero")
    void testAdd_WithValueSource(int value) {
        assertEquals(value, calculatorService.add(value, 0));
    }
}
