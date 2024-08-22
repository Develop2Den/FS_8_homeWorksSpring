package HW_5.customer.service;

import HW_5.employer.api.dto.EmployerResponse;
import HW_5.enums.Role;
import HW_5.account.api.dto.AccountRequest;
import HW_5.account.api.dto.AccountResponse;
import HW_5.account.db.Account;
import HW_5.account.service.AccountService;
import HW_5.customer.api.dto.CustomerRequest;
import HW_5.customer.api.dto.CustomerResponse;
import HW_5.customer.db.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {

        Customer customer = modelMapper.map(customerRequest, Customer.class);

        if (customerRequest.getPassword() != null) {
            String plainPassword = customerRequest.getPassword().getPassword();
            customer.setPassword(passwordEncoder.encode(plainPassword));
        }

        if (customer.getRole() == null) {
            customer.setRole(Role.USER);
        }

        Customer savedCustomer = customerService.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponse.class);
    }


    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(id, new Customer(
                customer.getName(),
                customer.getEmail(),
                customer.getAge(),
                passwordEncoder.encode(customer.getPassword()),
                customer.getPhoneNumber(),
                customer.getRole() != null ? customer.getRole() : Role.USER
        ));
        return updatedCustomer != null ? modelMapper.map(updatedCustomer, CustomerResponse.class) : null;
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerService.getOne(id);
        return customer != null ? modelMapper.map(customer, CustomerResponse.class) : null;
    }

    public CustomerResponse getCurrentCustomer(String email) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return modelMapper.map(customer, CustomerResponse.class);
        } else {
            throw new UsernameNotFoundException("Customer not found with email: " + email);
        }
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable, String currentUserEmail) {

        Optional<Customer> currentUserOptional = customerService.findByEmail(currentUserEmail);

        if (currentUserOptional.isPresent()) {
            Customer currentUser = currentUserOptional.get();

            if (currentUser.getRole() == Role.ADMIN) {
                List<Customer> customers = customerService.findAllByRole(Role.USER);
                Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

                return customerPage.map(customer -> {
                    CustomerResponse response = modelMapper.map(customer, CustomerResponse.class);

                    if (customer.getAccounts() != null) {
                        response.setAccounts(customer.getAccounts().stream()
                                .map(account -> modelMapper.map(account, AccountResponse.class))
                                .collect(Collectors.toList()));
                    }

                    if (customer.getEmployers() != null) {
                        response.setEmployers(customer.getEmployers().stream()
                                .map(employer -> modelMapper.map(employer, EmployerResponse.class))
                                .collect(Collectors.toList()));
                    }
                    return response;
                });
            } else {
                CustomerResponse response = modelMapper.map(currentUser, CustomerResponse.class);

                if (currentUser.getAccounts() != null) {
                    response.setAccounts(currentUser.getAccounts().stream()
                            .map(account -> modelMapper.map(account, AccountResponse.class))
                            .collect(Collectors.toList()));
                }

                if (currentUser.getEmployers() != null) {
                    response.setEmployers(currentUser.getEmployers().stream()
                            .map(employer -> modelMapper.map(employer, EmployerResponse.class))
                            .collect(Collectors.toList()));
                }
                return new PageImpl<>(List.of(response), pageable, 1);
            }
        } else {
            return new PageImpl<>(List.of(), pageable, 1);
        }
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

