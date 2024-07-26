package HW_2.services;

import HW_2.DTO.EmployerDTO;
import HW_2.classes.Employer;
import HW_2.repository.EmployerDAORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployerService {

    private final EmployerDAORepository employerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployerService(EmployerDAORepository employerRepository, ModelMapper modelMapper) {
        this.employerRepository = employerRepository;
        this.modelMapper = modelMapper;
    }

    public EmployerDTO save(EmployerDTO employerDTO) {
        Employer employer = modelMapper.map(employerDTO, Employer.class);
        Employer savedEmployer = employerRepository.save(employer);
        return modelMapper.map(savedEmployer, EmployerDTO.class);
    }

    public boolean delete(EmployerDTO employerDTO) {
        if (employerRepository.existsById(employerDTO.getId())) {
            employerRepository.deleteById(employerDTO.getId());
            return true;
        }
        return false;
    }

    public void deleteAll(List<EmployerDTO> employerDTOs) {
        List<Employer> employers = employerDTOs.stream()
                .map(dto -> modelMapper.map(dto, Employer.class))
                .collect(Collectors.toList());
        employerRepository.deleteAll(employers);
    }

    public List<EmployerDTO> findAll() {
        return employerRepository.findAll().stream()
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteById(long id) {
        if (employerRepository.existsById(id)) {
            employerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public EmployerDTO getOne(long id) {
        return employerRepository.findById(id)
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .orElse(null);
    }

    public void saveAll(List<EmployerDTO> employerDTOs) {
        List<Employer> employers = employerDTOs.stream()
                .map(dto -> modelMapper.map(dto, Employer.class))
                .collect(Collectors.toList());
        employerRepository.saveAll(employers);
    }
}



