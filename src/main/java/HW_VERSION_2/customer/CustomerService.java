package HW_VERSION_2.customer;

import HW_VERSION_2.account.Account;
import HW_VERSION_2.account.AccountDaoRepository;
import HW_VERSION_2.employer.EmployerDAORepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private AccountDaoRepository accountRepository;
    @Autowired
    private CustomerDAORepository customerRepository;
    @Autowired
    private EmployerDAORepository employerRepository;

    public Customer save(Customer customer) {
        for (Account account : customer.getAccounts()) {
            accountRepository.save(account);
        }
        customer.getEmployers().forEach(employer -> {
            System.out.println("Saving employer: " + employer);
            employerRepository.save(employer);
        });
        System.out.println("Saving customer: " + customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long id, Customer updatedCustomer) {
        if (!customerRepository.existsById(id)) {
            return null;
        }

        Customer existingCustomer = customerRepository.findById(id).get();
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setAge(updatedCustomer.getAge());

        // Обновление аккаунтов
        existingCustomer.getAccounts().clear(); // Удаление старых аккаунтов
        existingCustomer.getAccounts().addAll(updatedCustomer.getAccounts());
        existingCustomer.getAccounts().forEach(account -> account.setCustomer(existingCustomer));
        accountRepository.saveAll(existingCustomer.getAccounts());

        // Обновление работодателей
        existingCustomer.getEmployers().clear(); // Удаление старых работодателей
        existingCustomer.getEmployers().addAll(updatedCustomer.getEmployers());
        employerRepository.saveAll(existingCustomer.getEmployers());

        return customerRepository.save(existingCustomer);
    }

    public boolean delete(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }

    public void deleteAll(List<Customer> customers) {
        customerRepository.deleteAll(customers);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public boolean deleteById(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Customer getOne(long id) {
        return customerRepository.findById(id).orElse(null);
    }
}


