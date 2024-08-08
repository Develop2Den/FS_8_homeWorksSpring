package HW_3.customer;

import HW_3.customer.db.Customer;
import HW_3.customer.db.CustomerDAORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CustomerDAORepositoryTest extends IntegrationTestBase{

    private static final Long CUSTOMER_ID = 1L;

    @Autowired
    private CustomerDAORepository customerDAORepository;

    @Test
    void testGetById(){

        Optional<Customer> customer = customerDAORepository.findById(CUSTOMER_ID);
        assertTrue(customer.isPresent());
        customer.ifPresent(entity -> assertEquals("Goga Gogi", entity.getName()));

    }

    @Test
    void testSave(){
        Customer customer = Customer.builder().name("pasha").email("ph@ph.com").age(45).build();
        customerDAORepository.save(customer);
        assertNotNull(customer.getId());
    }
}