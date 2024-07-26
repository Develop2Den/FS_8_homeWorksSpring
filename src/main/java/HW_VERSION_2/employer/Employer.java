package HW_VERSION_2.employer;

import HW_VERSION_2.utils.AbstractEntity;
import HW_VERSION_2.customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employer")
public class Employer extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany(mappedBy = "employers", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Customer> customers = new HashSet<>();

    public Employer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
