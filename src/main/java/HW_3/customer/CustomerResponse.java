package HW_3.customer;

import HW_3.account.AccountResponse;
import HW_3.employer.EmployerResponse;
import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String phone;
    private List<AccountResponse> accounts;
    private List<EmployerResponse> employers;
}
