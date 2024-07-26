package HW_2.classes;

import lombok.Data;
import jakarta.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
