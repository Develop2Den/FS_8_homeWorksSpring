package HW_2.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAORepository extends JpaRepository<Customer, Long> {
}
