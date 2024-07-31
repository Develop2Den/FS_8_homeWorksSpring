package HW_3.account;


import HW_3.customer.CustomerFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AccountController {

    private final AccountFacade accountFacade;
    private final CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        try {
            AccountResponse accountResponse = accountFacade.createAccount(accountRequest);
            return ResponseEntity.ok(accountResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountResponse> deposit(@RequestParam String number, @RequestParam Double amount) {
        AccountResponse accountResponse = accountFacade.deposit(number, amount);
        if (accountResponse != null) {
            return ResponseEntity.ok(accountResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@RequestParam String number, @RequestParam Double amount) {
        AccountResponse accountResponse = accountFacade.withdraw(number, amount);
        if (accountResponse != null) {
            return ResponseEntity.ok(accountResponse);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromNumber, @RequestParam String toNumber, @RequestParam Double amount) {
        boolean success = accountFacade.transfer(fromNumber, toNumber, amount);
        if (success) {
            return ResponseEntity.ok("Transfer successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transfer failed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        boolean deleted = accountFacade.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.ok("Account deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable long id) {
        AccountResponse accountResponse = accountFacade.getAccount(id);
        if (accountResponse != null) {
            return ResponseEntity.ok(accountResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountFacade.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}





