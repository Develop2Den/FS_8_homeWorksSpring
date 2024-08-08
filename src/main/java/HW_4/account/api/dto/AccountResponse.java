package HW_4.account.api.dto;

import HW_4.customer.views.Views;
import HW_4.enums.Currency;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
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
