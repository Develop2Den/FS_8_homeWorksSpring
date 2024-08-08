package HW_3.employer.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDAORepository extends JpaRepository<Employer,Long> {

}
