package HW_4.customer.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDAORepository extends JpaRepository<Customer, Long> {
    Page<Customer> findAll(Pageable pageable);
}
