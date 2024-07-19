package HW_2.DAO.DAOinterfaceImpl;

import HW_2.DAO.DAOinterface.DAO;
import HW_2.classes.Employer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployerDAO implements DAO<Employer> {

    private final List<Employer> employers = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Employer save(Employer employer) {
        if (employer.getId() == null){
            employer.setId(idGenerator.getAndIncrement());
        }
        employers.add(employer);
        return employer;
    }

    @Override
    public boolean delete(Employer employer) {
        return employers.remove(employer);
    }

    @Override
    public void deleteAll(List<Employer> entities) {
        employers.removeAll(entities);
    }

    @Override
    public void saveAll(List<Employer> entities) {
        entities.forEach(this::save);
    }

    @Override
    public List<Employer> findAll() {
        return new ArrayList<>(employers);
    }

    @Override
    public boolean deleteById(long id) {
        return employers.removeIf(em -> em.getId() == id);
    }

    @Override
    public Employer getOne(long id) {
        return employers.stream()
                .filter(em -> em.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
