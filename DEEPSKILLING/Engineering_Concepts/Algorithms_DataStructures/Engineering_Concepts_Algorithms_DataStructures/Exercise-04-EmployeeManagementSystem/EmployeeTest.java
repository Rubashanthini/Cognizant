public class EmployeeTest {
    public static void main(String[] args) {
        EmployeeArray employeeArray = new EmployeeArray(10);

        employeeArray.addEmployee(new Employee(1, "John Doe", "Manager", 75000.00));
        employeeArray.addEmployee(new Employee(2, "Jane Smith", "Developer", 65000.00));
        employeeArray.addEmployee(new Employee(3, "Mike Brown", "Tester", 55000.00));

        System.out.println("All Employees After Adding:");
        employeeArray.traverseEmployees();

        System.out.println("\nSearching for Employee ID 2:");
        Employee found = employeeArray.searchEmployee(2);
        System.out.println(found != null ? found : "Employee not found");

        System.out.println("\nDeleting Employee ID 1:");
        employeeArray.deleteEmployee(1);

        System.out.println("\nAll Employees After Deletion:");
        employeeArray.traverseEmployees();

        System.out.println("\nTotal Employees Remaining: " + employeeArray.getSize());
    }
}
