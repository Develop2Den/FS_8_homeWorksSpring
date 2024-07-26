package HW_VERSION_2.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountDaoRepository accountRepository;

    @Autowired
    public AccountService(AccountDaoRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public boolean delete(Account account) {
        if (accountRepository.existsById(account.getId())) {
            accountRepository.delete(account);
            return true;
        }
        return false;
    }

    public void deleteAll(List<Account> accounts) {
        accountRepository.deleteAll(accounts);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public boolean deleteById(long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Account getOne(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account deposit(String number, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);
        if (optionalAccount.isPresent() && amount > 0) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account withdraw(String number, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);
        if (optionalAccount.isPresent() && amount > 0) {
            Account account = optionalAccount.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                return accountRepository.save(account);
            }
        }
        return null;
    }

    public boolean transfer(String fromNumber, String toNumber, Double amount) {
        Optional<Account> fromAccountOpt = accountRepository.findByNumber(fromNumber);
        Optional<Account> toAccountOpt = accountRepository.findByNumber(toNumber);

        if (fromAccountOpt.isPresent() && toAccountOpt.isPresent() && amount > 0) {
            Account fromAccount = fromAccountOpt.get();
            Account toAccount = toAccountOpt.get();

            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);
                return true;
            }
        }
        return false;
    }
}


