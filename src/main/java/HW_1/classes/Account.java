package HW_1.classes;

import HW_1.enums.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import java.util.UUID;

@Data
public class Account {
    private Long id;
    private String number;
    private Currency currency;
    private Double balance = 0.0;
    @JsonBackReference
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.customer = customer;
    }
}
