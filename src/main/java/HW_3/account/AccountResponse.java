package HW_3.account;

import HW_3.enums.Currency;
import lombok.Data;

@Data
public class AccountResponse {

    private Long id;
    private String number;
    private Currency currency;
    private Double balance;
    private Long customerId;
}
