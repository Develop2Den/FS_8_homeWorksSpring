package HW_2.employer;

import HW_2.utils.AbstractEntity;
import HW_2.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany(mappedBy = "employers")
    private List<Customer> customers = new ArrayList<>();

    public Employer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
