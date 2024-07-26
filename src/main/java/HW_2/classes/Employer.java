package HW_2.classes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employer")
public class Employer extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @ManyToMany(mappedBy = "employers", cascade = CascadeType.ALL)
    private Set<Customer> customers = new HashSet<>();

    public Employer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}


