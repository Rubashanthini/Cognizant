import java.util.HashMap;
import java.util.Map;


public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> customerDatabase = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Seed with some sample data
        customerDatabase.put("C001", new Customer("C001", "John Smith", "john.smith@example.com"));
        customerDatabase.put("C002", new Customer("C002", "Priya Rao", "priya.rao@example.com"));
        customerDatabase.put("C003", new Customer("C003", "Wei Chen", "wei.chen@example.com"));
    }

    @Override
    public Customer findCustomerById(String id) {
        return customerDatabase.get(id);
    }
}
