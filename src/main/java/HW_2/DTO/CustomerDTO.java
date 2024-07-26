package HW_2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private List<AccountDTO> accounts;
    private Set<EmployerDTO> employers;
}

