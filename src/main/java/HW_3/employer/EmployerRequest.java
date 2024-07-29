package HW_3.employer;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployerRequest {

    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Size(min = 3, message = "Address must be at least 3 characters long")
    private String address;
}
