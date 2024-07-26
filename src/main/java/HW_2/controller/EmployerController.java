package HW_2.controller;

import HW_2.DTO.EmployerDTO;
import HW_2.services.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EmployerDTO> createEmployer(@RequestBody EmployerDTO employerDTO) {
        try {
            EmployerDTO savedEmployer = employerService.save(employerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerDTO> updateEmployer(@PathVariable Long id, @RequestBody EmployerDTO employerDTO) {
        employerDTO.setId(id);
        EmployerDTO updatedEmployer = employerService.save(employerDTO);
        if (updatedEmployer != null) {
            return ResponseEntity.ok(updatedEmployer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        if (employerService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerDTO> getEmployerById(@PathVariable Long id) {
        EmployerDTO employerDTO = employerService.getOne(id);
        if (employerDTO != null) {
            return ResponseEntity.ok(employerDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployerDTO>> getAllEmployers() {
        List<EmployerDTO> employers = employerService.findAll();
        return ResponseEntity.ok(employers);
    }
}

