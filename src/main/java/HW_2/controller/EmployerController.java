package HW_2.controller;

import HW_2.classes.Account;
import HW_2.classes.Employer;
import HW_2.services.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService employerService;

    @PostMapping
    public Employer createEmployer(@RequestBody Employer employer) {
        return employerService.save(employer);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployer(@PathVariable long id) {
        return employerService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Employer getAccount(@PathVariable long id) {
        return employerService.getOne(id);
    }

    @GetMapping
    public List<Employer> getAllAccounts() {
        return employerService.findAll();
    }


}
