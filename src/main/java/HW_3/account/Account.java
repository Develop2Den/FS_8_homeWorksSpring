package HW_3.account;

import HW_3.utils.AbstractEntity;
import HW_3.customer.Customer;
import HW_3.enums.Currency;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "customer")
public class Account extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Double balance = 0.0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.customer = customer;
    }
}
