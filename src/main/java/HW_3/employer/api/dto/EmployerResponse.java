package HW_3.employer.api.dto;

import HW_3.customer.api.dto.CustomerResponse;
import HW_3.customer.views.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
public class EmployerResponse {

    @JsonView(Views.Info.class)
    private Long id;
    @JsonView(Views.Info.class)
    private String name;
    @JsonView(Views.Info.class)
    private String address;
    @JsonView(Views.Info.class)
    @JsonIgnore
    private List<CustomerResponse> customers;
}
