package HW_3.account;

import HW_3.customer.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountDaoRepositoryTest extends IntegrationTestBase {

    private static final Long accountId = 1L;

    @Autowired
    AccountDaoRepository repo;

    @Test
    void testById(){
       Optional<Account> account = repo.findById(accountId);
       assertTrue(account.isPresent());

    }

}