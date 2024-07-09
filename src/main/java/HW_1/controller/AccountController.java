package HW_1.controller;

import HW_1.classes.Account;
import HW_1.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

//    @Autowired
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/deposit")
    public Account deposit(@RequestParam String number, @RequestParam Double amount) {
        // Логіка для поповнення рахунку
        return null;
    }

    @PutMapping("/withdraw")
    public Account withdraw(@RequestParam String number, @RequestParam Double amount) {
        // Логіка для зняття грошей
        return null;
    }

    @PutMapping("/transfer")
    public void transfer(@RequestParam String fromNumber, @RequestParam String toNumber, @RequestParam Double amount) {
        // Логіка для переказу грошей
    }

    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable long id) {
        return accountService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getOne(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }
}
