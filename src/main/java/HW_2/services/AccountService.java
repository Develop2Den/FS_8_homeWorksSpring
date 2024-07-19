package HW_2.services;

import HW_2.DAO.DAOinterfaceImpl.AccountDAO;
import HW_2.classes.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountDAO accountDao;

    @Autowired
    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    public Account save(Account account) {
        return accountDao.save(account);
    }

    public boolean delete(Account account) {
        return accountDao.delete(account);
    }

    public void deleteAll(List<Account> accounts) {
        accountDao.deleteAll(accounts);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public boolean deleteById(long id) {
        return accountDao.deleteById(id);
    }

    public Account getOne(long id) {
        return accountDao.getOne(id);
    }

    public Account deposit(String number, Double amount) {
        Account account = accountDao.findByNumber(number);
        if (account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            return accountDao.save(account);
        }
        return null;
    }

    public Account withdraw(String number, Double amount) {
        Account account = accountDao.findByNumber(number);
        if (account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return accountDao.save(account);
        }
        return null;
    }

    public boolean transfer(String fromNumber, String toNumber, Double amount) {
        Account fromAccount = accountDao.findByNumber(fromNumber);
        Account toAccount = accountDao.findByNumber(toNumber);
        if (fromAccount != null && toAccount != null && amount > 0 && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountDao.save(fromAccount);
            accountDao.save(toAccount);
            return true;
        }
        return false;
    }
}
