package HW_2.services;

import HW_2.DTO.AccountDTO;
import HW_2.classes.Account;
import HW_2.classes.Customer;
import HW_2.exception.InsufficientFundsException;
import HW_2.exception.ResourceNotFoundException;
import HW_2.repository.AccountDaoRepository;
import HW_2.repository.CustomerDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private final AccountDaoRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountService(AccountDaoRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    public AccountDTO save(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        Account savedAccount = accountRepository.save(account);
        return modelMapper.map(savedAccount, AccountDTO.class);
    }

    public boolean delete(AccountDTO accountDTO) {
        if (accountRepository.existsById(accountDTO.getId())) {
            accountRepository.deleteById(accountDTO.getId());
            return true;
        }
        return false;
    }

    public void deleteAll(List<AccountDTO> accountDTOs) {
        List<Account> accounts = accountDTOs.stream()
                .map(dto -> modelMapper.map(dto, Account.class))
                .toList();
        accountRepository.deleteAll(accounts);
    }

    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();
    }

    public boolean deleteById(long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AccountDTO getOne(long id) {
        return accountRepository.findById(id)
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .orElse(null);
    }

    public AccountDTO deposit(String number, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);
        if (optionalAccount.isPresent() && amount > 0) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance() + amount);
            Account updatedAccount = accountRepository.save(account);
            return modelMapper.map(updatedAccount, AccountDTO.class);
        }
        return null;
    }

    public AccountDTO withdraw(String number, Double amount) {
        Optional<Account> optionalAccount = accountRepository.findByNumber(number);
        if (optionalAccount.isPresent() && amount > 0) {
            Account account = optionalAccount.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                Account updatedAccount = accountRepository.save(account);
                return modelMapper.map(updatedAccount, AccountDTO.class);
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





