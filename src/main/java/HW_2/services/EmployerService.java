package HW_2.services;

import HW_2.DAO.DAOinterfaceImpl.EmployerDAO;
import HW_2.classes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    private final EmployerDAO employerDAO;

    @Autowired
    public EmployerService(EmployerDAO employerDAO) {
        this.employerDAO = employerDAO;
    }

    public Employer save(Employer employer) {return employerDAO.save(employer);}
    public boolean delete(Employer employer) {return employerDAO.delete(employer);}
    public void deleteAll(List<Employer> employers) {employerDAO.deleteAll(employers);}
    public List<Employer> findAll() {return employerDAO.findAll();}
    public boolean deleteById(long id) {return employerDAO.deleteById(id);}
    public Employer getOne(long id) {return employerDAO.getOne(id);}

}

