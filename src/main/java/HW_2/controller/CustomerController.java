package HW_2.controller;

import HW_2.classes.Account;
import HW_2.classes.Customer;
import HW_2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

//    @Autowired
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable long id) {
        return customerService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return customerService.getOne(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @PostMapping("/{customerId}/accounts")
    public Account createAccountForCustomer(@PathVariable long customerId, @RequestBody Account account) {
        Customer customer = customerService.getOne(customerId);
        if (customer != null) {
            account.setCustomer(customer);
//             accountService.save(account);
            customer.getAccounts().add(account);
            customerService.save(customer);
            return account;
        }
        return null;
    }

    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public boolean deleteAccountFromCustomer(@PathVariable long customerId, @PathVariable long accountId) {
        Customer customer = customerService.getOne(customerId);
        if (customer != null) {
            return customer.getAccounts().removeIf(account -> account.getId() == accountId);
        }
        return false;
    }
}
