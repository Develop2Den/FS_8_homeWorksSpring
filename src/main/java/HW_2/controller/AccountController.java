package HW_2.controller;

import HW_2.classes.Account;
import HW_2.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/deposit")
    public Account deposit(@RequestParam String number, @RequestParam Double amount) {
        return accountService.deposit(number, amount);
    }

    @PutMapping("/withdraw")
    public Account withdraw(@RequestParam String number, @RequestParam Double amount) {
        return accountService.withdraw(number, amount);
    }

    @PutMapping("/transfer")
    public boolean transfer(@RequestParam String fromNumber, @RequestParam String toNumber, @RequestParam Double amount) {
        return accountService.transfer(fromNumber, toNumber, amount);
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
