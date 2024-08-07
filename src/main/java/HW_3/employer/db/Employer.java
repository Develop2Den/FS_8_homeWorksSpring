package HW_3.employer.db;

import HW_3.utils.AbstractEntity;
import HW_3.customer.db.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employer extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "employers")
    @JsonIgnore
    private List<Customer> customers = new ArrayList<>();

    public Employer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}


