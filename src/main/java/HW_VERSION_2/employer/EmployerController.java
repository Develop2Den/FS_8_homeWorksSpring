package HW_VERSION_2.employer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employers")
@CrossOrigin(origins = "*")
public class EmployerController {
    private final EmployerService employerService;

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        Employer savedEmployer = employerService.save(employer);
        return ResponseEntity.ok(savedEmployer);
    }

    @PutMapping("/{id}")
    public Employer updateEmployer(@PathVariable Long id, @RequestBody Employer employer) {
        employer.setId(id);
        return employerService.save(employer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        if (employerService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        Employer employer = employerService.getOne(id);
        if (employer != null) {
            return ResponseEntity.ok(employer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = employerService.findAll();
        return ResponseEntity.ok(employers);
    }
}
