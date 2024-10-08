package HW_4.customer.api;

import HW_4.account.api.dto.AccountRequest;
import HW_4.account.api.dto.AccountResponse;
import HW_4.customer.api.dto.CustomerRequest;
import HW_4.customer.api.dto.CustomerResponse;
import HW_4.customer.db.Customer;
import HW_4.customer.service.CustomerFacade;
import HW_4.customer.service.CustomerService;
import HW_4.customer.views.Views;
import HW_4.enums.Role;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Log4j2
@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        try {
            CustomerResponse customerResponse = customerFacade.createCustomer(customerRequest);
            return ResponseEntity.ok(customerResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerFacade.updateCustomer(id, customerRequest);
        return customerResponse != null ? ResponseEntity.ok(customerResponse) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable long id) {
        boolean deleted = customerFacade.deleteCustomer(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @GetMapping("/{id}")
    @JsonView(Views.DetailInfo.class)
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable long id) {
        CustomerResponse customerResponse = customerFacade.getCustomer(id);
        return customerResponse != null ? ResponseEntity.ok(customerResponse) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/me")
    @JsonView(Views.Info.class)
    public ResponseEntity<CustomerResponse> getCurrentCustomer(Authentication authentication) {
        String email = authentication.getName();
        CustomerResponse response = customerFacade.getCurrentCustomer(email);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    @JsonView(Views.Info.class)
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (userDetails == null || !userDetails.isEnabled()) {
            log.warn("Unauthorized access attempt by anonymous or disabled user");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            Page<CustomerResponse> customerPage = customerFacade.getAllCustomers(PageRequest.of(page, size), userDetails.getUsername());
            log.info("Customers: {}", customerPage);
            log.info("User: {}", userDetails.getUsername());
            return ResponseEntity.ok(customerPage);
        } catch (Exception e) {
            log.error("Error occurred while fetching customers: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @GetMapping
//    @JsonView(Views.Info.class)
//    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//         , @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        Page<CustomerResponse> customerPage = customerFacade.getAllCustomers(PageRequest.of(page, size), userDetails.getUsername());
//        log.info("Customers: {}", customerPage);
//        log.warn(userDetails.getUsername());
//        log.info("Logging is working!");
//        return ResponseEntity.ok(customerPage);
//    }

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








