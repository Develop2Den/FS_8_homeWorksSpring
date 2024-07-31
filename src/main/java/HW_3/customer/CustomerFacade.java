package HW_3.customer;

import HW_3.account.Account;
import HW_3.account.AccountRequest;
import HW_3.account.AccountResponse;
import HW_3.account.AccountService;
import HW_3.employer.EmployerResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;

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
    }

    public boolean deleteCustomer(Long id) {
        return customerService.deleteById(id);
    }

    public AccountResponse createAccountForCustomer(Long customerId, AccountRequest accountRequest) {
        // Получаем клиента
        Customer customer = customerService.getOne(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        // Создаем аккаунт с помощью конструктора
        Account account = new Account(accountRequest.getCurrency(), customer);

        // Устанавливаем начальный баланс из запроса
        account.setBalance(accountRequest.getBalance());

        // Сохраняем аккаунт
        Account savedAccount = accountService.save(account);

        // Добавляем аккаунт к клиенту
        customer.getAccounts().add(savedAccount);
        customerService.save(customer);

        // Возвращаем ответ
        return modelMapper.map(savedAccount, AccountResponse.class);
    }

//    public AccountResponse createAccountForCustomer(Long customerId, AccountRequest accountRequest) {
//        Customer customer = customerService.getOne(customerId);
//        if (customer == null) {
//            throw new IllegalArgumentException("Customer not found");
//        }
//        Account account = modelMapper.map(accountRequest, Account.class);
//        account.setCustomer(customer);
//        Account savedAccount = accountService.save(account);
//        customer.getAccounts().add(savedAccount);
//        customerService.save(customer);
//        return modelMapper.map(savedAccount, AccountResponse.class);
//    }

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


