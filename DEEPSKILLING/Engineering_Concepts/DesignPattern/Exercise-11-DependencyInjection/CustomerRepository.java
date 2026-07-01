
public interface CustomerRepository {
    /**
     * Finds a customer by their unique identifier.
     *
     * @param id the customer's ID
     * @return the matching Customer, or null if not found
     */
    Customer findCustomerById(String id);
}
