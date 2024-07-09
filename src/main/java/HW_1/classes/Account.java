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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return id == account.id && Objects.equals(number, account.number) && currency == account.currency && Objects.equals(balance, account.balance) && Objects.equals(customer, account.customer);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, number, currency, balance, customer);
//    }
//
//    @Override
//    public String toString() {
//        return "Account{" +
//                "id=" + id +
//                ", number='" + number + '\'' +
//                ", currency=" + currency +
//                ", balance=" + balance +
//                ", customer=" + customer +
//                '}';
//    }
}
