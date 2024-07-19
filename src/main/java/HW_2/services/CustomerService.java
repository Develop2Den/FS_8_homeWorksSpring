package HW_2.services;

import HW_2.DAO.DAOinterfaceImpl.AccountDAO;
import HW_2.DAO.DAOinterfaceImpl.CustomerDAO;
import HW_2.classes.Account;
import HW_2.classes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDAO customerDao;
    private final AccountDAO accountDao;

    @Autowired
    public CustomerService(CustomerDAO customerDao, AccountDAO accountDao) {
        this.customerDao = customerDao;
        this.accountDao = accountDao;
    }

    public Customer save(Customer customer) {
        for (Account account : customer.getAccounts()) {
            accountDao.save(account);
        }
        return customerDao.save(customer);
    }

    public boolean delete(Customer customer) {
        return customerDao.delete(customer);
    }

    public void deleteAll(List<Customer> customers) {
        customerDao.deleteAll(customers);
    }

    public void saveAll(List<Customer> customers) {
        for (Customer customer : customers) {
            save(customer);
        }
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public boolean deleteById(long id) {
        return customerDao.deleteById(id);
    }

    public Customer getOne(long id) {
        return customerDao.getOne(id);
    }
}
