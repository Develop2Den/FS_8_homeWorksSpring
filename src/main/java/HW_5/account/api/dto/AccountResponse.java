package HW_5.account.api.dto;

import HW_5.enums.Currency;
import HW_5.customer.views.Views;
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
