package HW_1.services;

import HW_1.DAO.DAOinterfaceImpl.AccountDAO;
import HW_1.classes.Account;
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

    public void saveAll(List<Account> accounts) {
        accountDao.saveAll(accounts);
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
}
