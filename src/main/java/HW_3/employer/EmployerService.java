package HW_3.employer;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Log4j2
@RequiredArgsConstructor
@Service
public class EmployerService {

    private final EmployerDAORepository employerDAORepository;

    public Employer save(Employer employer) {
        return employerDAORepository.save(employer);
    }

    public boolean deleteById(long id) {
        if (employerDAORepository.existsById(id)) {
            employerDAORepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Employer getOne(long id) {
        return employerDAORepository.findById(id).orElse(null);
    }

    public List<Employer> findAll() {
        return employerDAORepository.findAll();
    }
}






