package HW_5.customer.db;

import HW_5.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerDAORepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAll(Pageable pageable);
    List<Customer> findAllByRole(Role role);
    Optional<Customer> findByEmail(String email);
}
