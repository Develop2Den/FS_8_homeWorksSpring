package HW_2.controller;

import HW_2.DTO.AccountDTO;
import HW_2.DTO.CustomerDTO;
import HW_2.classes.Account;
import HW_2.classes.Customer;
import HW_2.services.CustomerService;
import HW_2.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.modelmapper.ModelMapper;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(id);
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        boolean isDeleted = customerService.deleteById(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable long id) {
        CustomerDTO customerDTO = customerService.getOne(id);
        return customerDTO != null ? ResponseEntity.ok(customerDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<?> createAccountForCustomer(@PathVariable Long customerId, @RequestBody AccountDTO accountDTO) {
        try {
            CustomerDTO customerDTO = customerService.getOne(customerId);
            if (customerDTO != null) {
                Account account = modelMapper.map(accountDTO, Account.class);
                account.setNumber(UUID.randomUUID().toString());
                account.setCustomer(modelMapper.map(customerDTO, Customer.class));
                account.setBalance(account.getBalance() != null ? account.getBalance() : 0.0);
                accountService.save(accountDTO);
                customerDTO.getAccounts().add(accountDTO);
                customerService.save(customerDTO);
                return ResponseEntity.ok(accountDTO);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating account: " + e.getMessage());
        }
    }

    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccountFromCustomer(@PathVariable long customerId, @PathVariable long accountId) {
        CustomerDTO customerDTO = customerService.getOne(customerId);
        if (customerDTO != null) {
            AccountDTO accountToRemove = customerDTO.getAccounts().stream()
                    .filter(account -> account.getId().equals(accountId))
                    .findFirst().orElse(null);

            if (accountToRemove != null) {
                customerDTO.getAccounts().remove(accountToRemove);
                customerService.save(customerDTO);
                accountService.delete(accountToRemove);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}



