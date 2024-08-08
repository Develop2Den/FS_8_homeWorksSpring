package HW_4.customer.service;

import HW_4.account.api.dto.AccountRequest;
import HW_4.account.api.dto.AccountResponse;
import HW_4.account.db.Account;
import HW_4.account.service.AccountService;
import HW_4.employer.api.dto.EmployerResponse;
import HW_4.customer.api.dto.CustomerRequest;
import HW_4.customer.api.dto.CustomerResponse;
import HW_4.customer.db.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponse.class);
    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return updatedCustomer != null ? modelMapper.map(updatedCustomer, CustomerResponse.class) : null;
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerService.getOne(id);
        return customer != null ? modelMapper.map(customer, CustomerResponse.class) : null;
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerService.findAll(pageable);

        log.info("Mapping Customer: {}", customerPage);

        return customerPage.map(customer -> {
            CustomerResponse response = modelMapper.map(customer, CustomerResponse.class);

            if (customer.getAccounts() != null) {
                response.setAccounts(customer.getAccounts().stream()
                        .map(account -> modelMapper.map(account, AccountResponse.class))
                        .collect(Collectors.toList()));
                log.info("Mapped Accounts: {}", response.getAccounts());
            }

            if (customer.getEmployers() != null) {
                response.setEmployers(customer.getEmployers().stream()
                        .map(employer -> modelMapper.map(employer, EmployerResponse.class))
                        .collect(Collectors.toList()));
                log.info("Mapped Employers: {}", response.getEmployers());
            }
            log.info("CustomerResponses: {}", response);
            return response;
        });
    }

    public boolean deleteCustomer(Long id) {
        return customerService.deleteById(id);
    }

    public AccountResponse createAccountForCustomer(Long customerId, AccountRequest accountRequest) {

        Customer customer = customerService.getOne(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        Account account = new Account(accountRequest.getCurrency(), customer);

        account.setBalance(accountRequest.getBalance());

        Account savedAccount = accountService.save(account);

        customer.getAccounts().add(savedAccount);
        customerService.save(customer);

        return modelMapper.map(savedAccount, AccountResponse.class);
    }

    public boolean deleteAccountFromCustomer(Long customerId, Long accountId) {
        Customer customer = customerService.getOne(customerId);
        if (customer != null) {
            Account accountToRemove = customer.getAccounts().stream()
                    .filter(account -> account.getId().equals(accountId))
                    .findFirst().orElse(null);
            if (accountToRemove != null) {
                customer.getAccounts().remove(accountToRemove);
                customerService.save(customer);
                accountService.deleteById(accountId);
                return true;
            }
        }
        return false;
    }
}


