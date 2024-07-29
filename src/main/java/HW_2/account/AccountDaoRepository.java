package HW_2.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountDaoRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(String number);
}
