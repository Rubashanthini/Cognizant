
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    /**
     * Constructor Injection: the CustomerRepository dependency is
     * passed in from the outside.
     *
     * @param customerRepository the repository implementation to use
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Looks up a customer by ID via the injected repository.
     *
     * @param id the customer's ID
     * @return a human-readable result describing the lookup outcome
     */
    public String getCustomerDetails(String id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            return "No customer found with ID: " + id;
        }
        return "Found customer -> " + customer;
    }
}
