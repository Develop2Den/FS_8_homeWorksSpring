package HW_3.account.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDaoRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
}
