package HW_2.repository;

import HW_2.classes.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountDaoRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(String number);
}
