package HW_2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {
    private Long id;
    private String name;
    private String address;
    private Set<CustomerDTO> customers;
}

