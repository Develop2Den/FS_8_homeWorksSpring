package HW_3.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDaoRepository extends JpaRepository<Account, Long> {
    Account findByNumber(String number);
}
