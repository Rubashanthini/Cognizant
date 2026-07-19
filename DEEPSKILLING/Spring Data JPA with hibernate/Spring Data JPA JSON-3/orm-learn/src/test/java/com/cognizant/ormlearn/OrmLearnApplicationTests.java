package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.ProductService;
import com.cognizant.ormlearn.service.QuizService;
import com.cognizant.ormlearn.util.ProductSearchCriteria;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrmLearnApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(OrmLearnApplicationTests.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
        // Verifies the Spring application context starts successfully.
    }

    // ---------- HANDS-ON 1 ----------
    @Test
    void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        logger.info("testGetAllEmployees -> count={}", employees.size());
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    // ---------- HANDS-ON 2 ----------
    @Test
    void testGetAllPermanentEmployees() {
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        logger.info("testGetAllPermanentEmployees -> count={}", employees.size());
        assertNotNull(employees);
        employees.forEach(e -> assertTrue(e.getPermanent()));
    }

    @Test
    void testPermanentEmployeesFetchJoin() {
        List<Employee> employees = employeeService.getPermanentEmployeesUsingFetchJoin();
        logger.info("testPermanentEmployeesFetchJoin -> count={}", employees.size());
        assertNotNull(employees);
    }

    // ---------- HANDS-ON 3 ----------
    @Test
    void testQuizResult() {
        Attempt attempt = quizService.getQuizResult(1, 1);
        logger.info("testQuizResult -> attemptId={}, questionsAnswered={}",
                attempt.getAttemptId(), attempt.getAttemptQuestions().size());
        assertNotNull(attempt);
        assertFalse(attempt.getAttemptQuestions().isEmpty());
    }

    // ---------- HANDS-ON 4 ----------
    @Test
    void testAverageSalary() {
        Double avg = employeeService.getAverageSalary();
        logger.info("testAverageSalary -> {}", avg);
        assertNotNull(avg);
        assertTrue(avg > 0);
    }

    @Test
    void testAverageSalaryByDepartment() {
        Double avg = employeeService.getAverageSalary(1);
        logger.info("testAverageSalaryByDepartment(1) -> {}", avg);
        assertNotNull(avg);
    }

    // ---------- HANDS-ON 5 ----------
    @Test
    void testNativeQueryAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployeesNative();
        logger.info("testNativeQueryAllEmployees -> count={}", employees.size());
        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    // ---------- HANDS-ON 6 ----------
    @Test
    void testCriteriaSearch() {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setRam(16);
        List<Product> products = productService.searchProducts(criteria);
        logger.info("testCriteriaSearch(ram=16) -> count={}", products.size());
        assertNotNull(products);
        products.forEach(p -> assertEquals(16, p.getRam()));
    }
}
