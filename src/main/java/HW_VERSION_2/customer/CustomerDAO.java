//package HW_VERSION_2.customer;
//
//import HW_VERSION_2.DAO.DAOinterface.DAO;
//import HW_VERSION_2.customer.Customer;
//import org.springframework.stereotype.Repository;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Repository
//public class CustomerDAO implements DAO<Customer> {
//
//    private final List<Customer> customers = new ArrayList<>();
//    private final AtomicLong idGenerator = new AtomicLong(1);
//
//    @Override
//    public Customer save(Customer customer) {
//        if (customer.getId() == null) {
//            customer.setId(idGenerator.getAndIncrement());
//        } else {
//            Optional<Customer> existingCustomer = customers.stream()
//                    .filter(c -> c.getId().equals(customer.getId()))
//                    .findFirst();
//            existingCustomer.ifPresent(customers::remove);
//        }
//        customers.add(customer);
//        return customer;
//    }
//
//    @Override
//    public boolean delete(Customer customer) {
//        return customers.remove(customer);
//    }
//
//    @Override
//    public void deleteAll(List<Customer> entities) {
//        customers.removeAll(entities);
//    }
//
//    @Override
//    public void saveAll(List<Customer> entities) {
//        entities.forEach(this::save);
//    }
//
//    @Override
//    public List<Customer> findAll() {
//        return new ArrayList<>(customers);
//    }
//
//    @Override
//    public boolean deleteById(long id) {
//        return customers.removeIf(customer -> customer.getId() == id);
//    }
//
//    @Override
//    public Customer getOne(long id) {
//        Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findFirst();
//        return customer.orElse(null);
//    }
//}
