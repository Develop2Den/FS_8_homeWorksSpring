package HW_4.customer.api.dto;

import HW_4.account.api.dto.AccountResponse;
import HW_4.employer.api.dto.EmployerResponse;
import HW_4.customer.views.Views;
import HW_4.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
public class CustomerResponse {

    @JsonView(Views.Info.class)
    private Long id;

    @JsonView(Views.Info.class)
    private String name;

    @JsonView(Views.DetailInfo.class)
    private String email;

    @JsonView(Views.DetailInfo.class)
    private Integer age;

    @JsonView(Views.DetailInfo.class)
    private String phoneNumber;

//    @JsonView(Views.DetailInfo.class)
//    private Role role;

    @JsonView(Views.Info.class)
    @ToString.Exclude
    private List<AccountResponse> accounts;

    @JsonView(Views.DetailInfo.class)
    @ToString.Exclude
    private List<EmployerResponse> employers;
}
