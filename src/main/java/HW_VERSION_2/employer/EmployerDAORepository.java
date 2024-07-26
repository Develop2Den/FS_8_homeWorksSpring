package HW_VERSION_2.employer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDAORepository extends JpaRepository<Employer, Long> {

}
