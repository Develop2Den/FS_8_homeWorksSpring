package HW_3.employer;

import HW_3.customer.CustomerResponse;
import lombok.Data;

import java.util.List;

@Data
public class EmployerResponse {

    private Long id;
    private String name;
    private String address;
    private List<CustomerResponse> customers;
}
