package HW_2.controller;

import HW_2.DTO.AccountDTO;
import HW_2.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        try {
            AccountDTO createdAccount = accountService.save(accountDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountDTO> deposit(@RequestParam String number, @RequestParam Double amount) {
        AccountDTO accountDTO = accountService.deposit(number, amount);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@RequestParam String number, @RequestParam Double amount) {
        AccountDTO accountDTO = accountService.withdraw(number, amount);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromNumber, @RequestParam String toNumber, @RequestParam Double amount) {
        boolean success = accountService.transfer(fromNumber, toNumber, amount);
        if (success) {
            return ResponseEntity.ok("Transfer successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        boolean deleted = accountService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Account deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable long id) {
        AccountDTO accountDTO = accountService.getOne(id);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }
}


