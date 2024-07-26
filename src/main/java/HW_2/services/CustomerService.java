package HW_2.services;

import HW_2.DTO.CustomerDTO;
import HW_2.classes.Account;
import HW_2.classes.Customer;
import HW_2.classes.Employer;
import HW_2.repository.AccountDaoRepository;
import HW_2.repository.CustomerDAORepository;
import HW_2.repository.EmployerDAORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private AccountDaoRepository accountRepository;
    @Autowired
    private CustomerDAORepository customerRepository;
    @Autowired
    private EmployerDAORepository employerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.getAccounts().forEach(account -> account.setCustomer(customer));
        customer.getEmployers().forEach(employer -> employerRepository.save(employer));
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(long id, CustomerDTO updatedCustomerDTO) {
        if (!customerRepository.existsById(id)) {
            return null;
        }

        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer == null) return null;

        modelMapper.map(updatedCustomerDTO, existingCustomer);

        existingCustomer.getAccounts().clear();
        existingCustomer.getAccounts().addAll(updatedCustomerDTO.getAccounts().stream()
                .map(accountDTO -> {
                    Account account = modelMapper.map(accountDTO, Account.class);
                    account.setCustomer(existingCustomer);
                    return account;
                })
                .toList());
        accountRepository.saveAll(existingCustomer.getAccounts());

        existingCustomer.getEmployers().clear();
        existingCustomer.getEmployers().addAll(updatedCustomerDTO.getEmployers().stream()
                .map(employerDTO -> modelMapper.map(employerDTO, Employer.class))
                .toList());
        employerRepository.saveAll(existingCustomer.getEmployers());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    public boolean delete(CustomerDTO customerDTO) {
        if (customerRepository.existsById(customerDTO.getId())) {
            customerRepository.deleteById(customerDTO.getId());
            return true;
        }
        return false;
    }

    public void deleteAll(List<CustomerDTO> customerDTOs) {
        List<Customer> customers = customerDTOs.stream()
                .map(dto -> modelMapper.map(dto, Customer.class))
                .toList();
        customerRepository.deleteAll(customers);
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .toList();
    }

    public boolean deleteById(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CustomerDTO getOne(long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? modelMapper.map(customer, CustomerDTO.class) : null;
    }
}





