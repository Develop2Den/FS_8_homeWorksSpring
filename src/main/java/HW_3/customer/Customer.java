package HW_3.customer;

import HW_3.account.Account;
import HW_3.employer.Employer;
import HW_3.utils.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = {"accounts", "password"})
public class Customer extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_employer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
    private Set<Employer> employers = new HashSet<>();

    public Customer(String name, String email, Integer age, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}



