//package HW_VERSION_2.account;
//
//import HW_VERSION_2.DAO.DAOinterface.DAO;
//import HW_VERSION_2.account.Account;
//import org.springframework.stereotype.Repository;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
//
//@Repository
//public class AccountDAO implements DAO<Account> {
//
//    private final List<Account> accounts = new ArrayList<>();
//    private final AtomicLong idGenerator = new AtomicLong(1);
//
//    @Override
//    public Account save(Account account) {
//        if (account.getId() == null) {
//            account.setId(idGenerator.incrementAndGet());
//        }
//        accounts.add(account);
//        return account;
//    }
//
//    @Override
//    public boolean delete(Account account) {
//        return accounts.remove(account);
//    }
//
//    @Override
//    public void deleteAll(List<Account> entities) {
//        accounts.removeAll(entities);
//    }
//
//    @Override
//    public void saveAll(List<Account> entities) {
//        entities.forEach(this::save);
//    }
//
//    @Override
//    public List<Account> findAll() {
//        return new ArrayList<>(accounts);
//    }
//
//    @Override
//    public boolean deleteById(long id) {
//        return accounts.removeIf(account -> account.getId() == id);
//    }
//
//    @Override
//    public Account getOne(long id) {
//        return accounts.stream()
//                .filter(account -> account.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Account findByNumber(String number) {
//        return accounts.stream()
//                .filter(account -> account.getNumber().equals(number))
//                .findFirst()
//                .orElse(null);
//    }
//}
