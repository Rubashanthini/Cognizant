package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.ProductService;
import com.cognizant.ormlearn.service.QuizService;
import com.cognizant.ormlearn.util.ProductSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(OrmLearnApplication.class);

    private final EmployeeService employeeService;
    private final QuizService quizService;
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("\n\n=========== SPRING DATA JPA HANDS-ON DEMO START ===========\n");

        demoHandsOn1();
        demoHandsOn2();
        demoHandsOn3();
        demoHandsOn4();
        demoHandsOn5();
        demoHandsOn6();

        logger.info("\n\n=========== SPRING DATA JPA HANDS-ON DEMO END =============\n");
    }

    // ================= HANDS-ON 1 =================
    private void demoHandsOn1() {
        logger.info("\n----- HANDS-ON 1: Introduction to HQL and JPQL -----");
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(e -> logger.info("Employee -> id={}, name={}, salary={}", e.getEmpId(), e.getEmpName(), e.getSalary()));
    }

    // ================= HANDS-ON 2 =================
    private void demoHandsOn2() {
        logger.info("\n----- HANDS-ON 2: Get all Permanent Employees using HQL -----");

        logger.info(">> Simple WHERE clause:");
        employeeService.getAllPermanentEmployees()
                .forEach(e -> logger.info("Permanent Employee -> {}", e.getEmpName()));

        logger.info(">> Using normal JOIN:");
        employeeService.getPermanentEmployeesUsingJoin()
                .forEach(e -> logger.info("Permanent Employee (JOIN) -> {}", e.getEmpName()));

        logger.info(">> Using JOIN FETCH (optimized, avoids N+1):");
        employeeService.getPermanentEmployeesUsingFetchJoin();
    }

    // ================= HANDS-ON 3 =================
    private void demoHandsOn3() {
        logger.info("\n----- HANDS-ON 3: Quiz Application -----");
        try {
            Attempt attempt = quizService.getQuizResult(1, 1);
            logger.info("Quiz result object graph loaded successfully for attemptId={}", attempt.getAttemptId());
        } catch (Exception ex) {
            logger.warn("Quiz demo skipped: {}", ex.getMessage());
        }
    }

    // ================= HANDS-ON 4 =================
    private void demoHandsOn4() {
        logger.info("\n----- HANDS-ON 4: Average Salary using HQL -----");
        logger.info("Average Salary (all employees) = {}", employeeService.getAverageSalary());
        logger.info("Average Salary (deptId=1)       = {}", employeeService.getAverageSalary(1));
    }

    // ================= HANDS-ON 5 =================
    private void demoHandsOn5() {
        logger.info("\n----- HANDS-ON 5: Native Query -----");
        logger.info("Total employees (native) = {}", employeeService.getAllEmployeesNative().size());
        logger.info("Average salary (native)   = {}", employeeService.getAverageSalaryNative());
    }

    // ================= HANDS-ON 6 =================
    private void demoHandsOn6() {
        logger.info("\n----- HANDS-ON 6: Criteria Query - Dynamic Product Search -----");

        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setRam(16);
        criteria.setOs("Windows 11");

        List<Product> products = productService.searchProducts(criteria);
        products.forEach(p -> logger.info("Product Match -> {} | RAM={}GB | OS={} | Price={}",
                p.getProductName(), p.getRam(), p.getOs(), p.getPrice()));
    }
}
