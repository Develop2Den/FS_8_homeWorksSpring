package HW_3.customer;

import HW_3.account.AccountFacade;
import HW_3.account.AccountRequest;
import HW_3.account.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerFacade customerFacade;
    private final AccountFacade accountFacade;
    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerFacade.createCustomer(customerRequest);
            return ResponseEntity.ok(customerResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerFacade.updateCustomer(id, customerRequest);
        return customerResponse != null ? ResponseEntity.ok(customerResponse) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable long id) {
        boolean deleted = customerFacade.deleteCustomer(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable long id) {
        CustomerResponse customerResponse = customerFacade.getCustomer(id);
        return customerResponse != null ? ResponseEntity.ok(customerResponse) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CustomerResponse> customerPage = customerFacade.getAllCustomers(PageRequest.of(page, size));
        return ResponseEntity.ok(customerPage);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<AccountResponse> createAccountForCustomer(@PathVariable Long customerId, @RequestBody AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = customerFacade.createAccountForCustomer(customerId, accountRequest);
            return ResponseEntity.ok(accountResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public ResponseEntity<Boolean> deleteAccountFromCustomer(@PathVariable Long customerId, @PathVariable Long accountId) {
        boolean deleted = customerFacade.deleteAccountFromCustomer(customerId, accountId);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}








