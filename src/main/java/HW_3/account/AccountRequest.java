package HW_3.account;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequest {

    @NotNull(message = "Currency cannot be null")
    private String currency;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    private Double balance;
}
