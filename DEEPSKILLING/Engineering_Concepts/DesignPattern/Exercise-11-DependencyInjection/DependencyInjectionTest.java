
public class DependencyInjectionTest {
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Test ===");

     
        CustomerRepository repository = new CustomerRepositoryImpl();

      
        CustomerService customerService = new CustomerService(repository);

  
        System.out.println(customerService.getCustomerDetails("C001"));
        System.out.println(customerService.getCustomerDetails("C002"));
        System.out.println(customerService.getCustomerDetails("C999"));
    }
}
