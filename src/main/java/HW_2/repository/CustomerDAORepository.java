package HW_2.repository;

import HW_2.classes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDAORepository extends JpaRepository<Customer, Long> {

}
