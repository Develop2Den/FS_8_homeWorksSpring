package HW_2.repository;

import HW_2.classes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDAORepository extends JpaRepository<Employer,Long> {

}
