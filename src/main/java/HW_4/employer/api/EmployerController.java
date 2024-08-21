package HW_4.employer.api;


import HW_4.employer.api.dto.EmployerRequest;
import HW_4.employer.api.dto.EmployerResponse;
import HW_4.employer.service.EmployerFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    private final EmployerFacade employerFacade;

    @PostMapping
    public ResponseEntity<EmployerResponse> createEmployer(@Valid @RequestBody EmployerRequest employerRequest) {
        EmployerResponse employerResponse = employerFacade.createEmployer(employerRequest);
        return ResponseEntity.ok(employerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerResponse> updateEmployer(@PathVariable Long id, @Valid @RequestBody EmployerRequest employerRequest) {
        EmployerResponse employerResponse = employerFacade.updateEmployer(id, employerRequest);
        return ResponseEntity.ok(employerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        boolean deleted = employerFacade.deleteEmployer(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponse> getEmployerById(@PathVariable Long id) {
        EmployerResponse employerResponse = employerFacade.getEmployerById(id);
        if (employerResponse != null) {
            return ResponseEntity.ok(employerResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployerResponse>> getAllEmployers() {
        List<EmployerResponse> employers = employerFacade.getAllEmployers();
        return ResponseEntity.ok(employers);
    }
}




