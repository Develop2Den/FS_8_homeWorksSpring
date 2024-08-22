package HW_5.account.db;

import HW_5.enums.Currency;
import HW_5.utils.AbstractEntity;
import HW_5.customer.db.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Double balance = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.customer = customer;
    }
}