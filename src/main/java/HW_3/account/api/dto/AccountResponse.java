package HW_3.account.api.dto;

import HW_3.customer.views.Views;
import HW_3.enums.Currency;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class AccountResponse {

    @JsonView(Views.Info.class)
    private Long id;
    @JsonView(Views.Info.class)
    private String number;
    @JsonView(Views.Info.class)
    private Currency currency;
    @JsonView(Views.Info.class)
    private Double balance;
    @JsonView(Views.Info.class)
    private Long customerId;
}
