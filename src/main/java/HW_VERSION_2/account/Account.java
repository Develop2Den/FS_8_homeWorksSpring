package HW_VERSION_2.account;

import HW_VERSION_2.utils.AbstractEntity;
import HW_VERSION_2.customer.Customer;
import HW_VERSION_2.enums.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Double balance = 0.0;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.customer = customer;
    }
}
