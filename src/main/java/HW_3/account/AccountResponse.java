package HW_3.account;

import lombok.Data;

@Data
public class AccountResponse {

    private Long id;
    private String number;
    private String currency;
    private Double balance;
    private Long customerId;
}
