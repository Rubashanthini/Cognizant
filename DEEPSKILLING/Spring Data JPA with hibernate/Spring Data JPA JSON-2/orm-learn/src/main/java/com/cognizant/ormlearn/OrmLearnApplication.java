package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.*;
import com.cognizant.ormlearn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    @Autowired
    private CountryService countryService;

    @Autowired
    private StockService stockService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillService skillService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=====================================================");
        System.out.println(" HANDS-ON 1: COUNTRY QUERY METHODS");
        System.out.println("=====================================================");
        testSearchCountriesContainingText();
        testSearchCountriesContainingTextOrderByAsc();
        testSearchCountriesStartingWithLetter();

        System.out.println("\n=====================================================");
        System.out.println(" HANDS-ON 2: STOCK QUERY METHODS");
        System.out.println("=====================================================");
        testFacebookStocksSeptember2019();
        testGoogleStocksAboveClosePrice();
        testTop3HighestVolumeStocks();
        testLowestThreeNetflixPrices();

        System.out.println("\n=====================================================");
        System.out.println(" HANDS-ON 4: EMPLOYEE / DEPARTMENT (MANY-TO-ONE)");
        System.out.println("=====================================================");
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();

        System.out.println("\n=====================================================");
        System.out.println(" HANDS-ON 5: DEPARTMENT -> EMPLOYEES (ONE-TO-MANY)");
        System.out.println("=====================================================");
        testGetDepartment();

        System.out.println("\n=====================================================");
        System.out.println(" HANDS-ON 6: EMPLOYEE <-> SKILL (MANY-TO-MANY)");
        System.out.println("=====================================================");
        testGetEmployeeWithSkills();
        testAddSkillToEmployee();

        System.out.println("\n=====================================================");
        System.out.println(" ALL HANDS-ON TEST METHODS COMPLETED SUCCESSFULLY");
        System.out.println("=====================================================");
    }

    // =========================================================
    // HANDS-ON 1
    // =========================================================

    private void testSearchCountriesContainingText() {
        System.out.println("\n--- testSearchCountriesContainingText('land') ---");
        List<Country> result = countryService.searchByNameContaining("land");
        result.forEach(System.out::println);
        // Expected: Iceland, Ireland, Poland-like entries containing 'land'
    }

    private void testSearchCountriesContainingTextOrderByAsc() {
        System.out.println("\n--- testSearchCountriesContainingTextOrderByAsc('in') ---");
        List<Country> result = countryService.searchByNameContainingOrderByAsc("in");
        result.forEach(System.out::println);
        // Expected: countries containing 'in', sorted A-Z
    }

    private void testSearchCountriesStartingWithLetter() {
        System.out.println("\n--- testSearchCountriesStartingWithLetter('I') ---");
        List<Country> result = countryService.searchByStartingLetter("I");
        result.forEach(System.out::println);
        // Expected: India, Indonesia, Iceland, Ireland, Italy
    }

    // =========================================================
    // HANDS-ON 2
    // =========================================================

    private void testFacebookStocksSeptember2019() {
        System.out.println("\n--- testFacebookStocksSeptember2019() ---");
        List<Stock> result = stockService.getFacebookStocksInSeptember2019();
        result.forEach(System.out::println);
        // Expected: 4 FB records from Sept 2019
    }

    private void testGoogleStocksAboveClosePrice() {
        System.out.println("\n--- testGoogleStocksAboveClosePrice(1250.0) ---");
        List<Stock> result = stockService.getGoogleStocksAboveClosePrice(1250.0);
        result.forEach(System.out::println);
        // Expected: GOOGL records with closePrice > 1250
    }

    private void testTop3HighestVolumeStocks() {
        System.out.println("\n--- testTop3HighestVolumeStocks() ---");
        List<Stock> result = stockService.getTop3HighestVolumeStocks();
        result.forEach(System.out::println);
        // Expected: AAPL, MSFT, AMZN (highest volumes) in descending order
    }

    private void testLowestThreeNetflixPrices() {
        System.out.println("\n--- testLowestThreeNetflixPrices() ---");
        List<Stock> result = stockService.getLowestThreeNetflixPrices();
        result.forEach(System.out::println);
        // Expected: 3 NFLX records with the lowest closing prices, ascending
    }

    // =========================================================
    // HANDS-ON 4
    // =========================================================

    private void testGetEmployee() {
        System.out.println("\n--- testGetEmployee(1) ---");
        Employee employee = employeeService.get(1L);
        System.out.println(employee);
        // Expected: Alice Johnson, salary=75000.0, deptId=1
    }

    private void testAddEmployee() {
        System.out.println("\n--- testAddEmployee() ---");
        Department department = departmentService.get(1L);
        Employee employee = new Employee("Frank Miller", 60000.00, department);
        Employee saved = employeeService.save(employee);
        System.out.println("Saved: " + saved);
        // Expected: new employee persisted with a generated empId
    }

    private void testUpdateEmployee() {
        System.out.println("\n--- testUpdateEmployee() ---");
        Employee employee = employeeService.get(2L);
        employee.setSalary(70000.00);
        Employee updated = employeeService.save(employee);
        System.out.println("Updated: " + updated);
        // Expected: Bob Smith salary updated to 70000.0
    }

    // =========================================================
    // HANDS-ON 5
    // =========================================================

    private void testGetDepartment() {
        System.out.println("\n--- testGetDepartment(1) ---");
        // With FetchType.EAGER (final, corrected implementation), the
        // employees collection is available here without a
        // LazyInitializationException, even though we are outside the
        // original @Transactional boundary.
        Department department = departmentService.get(1L);
        System.out.println("Department: " + department);
        System.out.println("Employees in department:");
        department.getEmployees().forEach(System.out::println);
        // Expected: Engineering department with its list of employees printed
    }

    // =========================================================
    // HANDS-ON 6
    // =========================================================

    private void testGetEmployeeWithSkills() {
        System.out.println("\n--- testGetEmployeeWithSkills(1) ---");
        // With FetchType.EAGER (final, corrected implementation), the
        // skills collection is available here without a
        // LazyInitializationException.
        Employee employee = employeeService.get(1L);
        System.out.println("Employee: " + employee);
        System.out.println("Skills:");
        employee.getSkills().forEach(System.out::println);
        // Expected: Alice Johnson's skills - Java, Spring Boot, SQL
    }

    private void testAddSkillToEmployee() {
        System.out.println("\n--- testAddSkillToEmployee() ---");
        Skill communication = skillService.get(4L);
        Employee updated = employeeService.addSkillToEmployee(1L, communication);
        System.out.println("Employee after adding skill: " + updated);
        System.out.println("Skills:");
        updated.getSkills().forEach(System.out::println);
        // Expected: Alice Johnson now also has 'Communication' skill
    }
}
