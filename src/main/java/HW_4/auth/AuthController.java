package HW_4.auth;

//import HW_4.customer.api.dto.CustomerRequest;
//import HW_4.customer.db.Customer;
//import HW_4.customer.service.CustomerService;
//import HW_4.enums.Role;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
////@CrossOrigin(origins = "http://localhost:3000")
//public class AuthController {
//
//    private final CustomerService customerService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AuthController(CustomerService customerService, PasswordEncoder passwordEncoder) {
//        this.customerService = customerService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @PostMapping("/auth")
//    public ResponseEntity<String> register(@RequestBody @Valid CustomerRequest customerRequest) {
//
//        if (customerService.findByEmail(customerRequest.getEmail()).isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
//        }
//
//        Customer newCustomer = new Customer(
//                customerRequest.getName(),
//                customerRequest.getEmail(),
//                customerRequest.getAge(),
//                passwordEncoder.encode(customerRequest.getPassword().getPassword()),
//                customerRequest.getPhoneNumber(),
//                customerRequest.getRole() != null ? customerRequest.getRole() : Role.USER
//        );
//        customerService.save(newCustomer);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//    }
//
//}

