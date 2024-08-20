package HW_4.customer.api.dto;

import HW_4.enums.Role;
import HW_4.password.PasswordValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequest {

    @Size(min = 2, message = "Name must be at least 2 characters long")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @NotNull(message = "Age cannot be null")
    private Integer age;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid")
    private String phoneNumber;

    @Valid
    private PasswordValidator password;

//    private Role role;
}

