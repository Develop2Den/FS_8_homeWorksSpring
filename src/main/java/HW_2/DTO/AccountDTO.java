package HW_2.DTO;

import HW_2.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String number;
    private Currency currency;
    private Double balance;
    private Long customerId;
}

