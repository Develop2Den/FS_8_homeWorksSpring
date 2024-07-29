//package HW_VERSION_2.employer;
//
//
//import HW_VERSION_2.DAO.DAOinterface.DAO;
//import org.springframework.stereotype.Repository;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Repository
//public class EmployerDAO implements DAO<Employer> {
//
//    private final List<Employer> employers = new ArrayList<>();
//    private final AtomicLong idGenerator = new AtomicLong(1);
//
//    @Override
//    public Employer save(Employer employer) {
//        if (employer.getId() == null) {
//            employer.setId(idGenerator.incrementAndGet());
//        }else {
//            Optional<Employer> existingCustomer = employers.stream()
//                    .filter(c -> c.getId().equals(employer.getId()))
//                    .findFirst();
//            existingCustomer.ifPresent(employers::remove);
//        }
//        employers.add(employer);
//        return employer;
//    }
//
//    @Override
//    public boolean delete(Employer employer) {
//        return employers.remove(employer);
//    }
//
//    @Override
//    public void deleteAll(List<Employer> entities) {
//        employers.removeAll(entities);
//    }
//
//    @Override
//    public void saveAll(List<Employer> entities)  {
//        entities.forEach(this::save);
//    }
//
//    @Override
//    public List<Employer> findAll() {
//        return new ArrayList<>(employers);
//    }
//
//    @Override
//    public boolean deleteById(long id)  {
//        return employers.removeIf(account -> account.getId() == id);
//    }
//
//    @Override
//    public Employer getOne(long id) {
//        return employers.stream()
//                .filter(em -> em.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//}
