package HW_3.employer;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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






