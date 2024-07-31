package HW_3.utils;

import HW_3.account.Account;
import HW_3.account.AccountResponse;
import HW_3.customer.Customer;
import HW_3.customer.CustomerResponse;
import HW_3.employer.Employer;
import HW_3.employer.EmployerResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.Collections;


@Configuration
@EnableJpaAuditing
public class JpaConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Customer, CustomerResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setEmail(source.getEmail());
                map().setAge(source.getAge());
                map().setPhone(source.getPhoneNumber());

                map().setAccounts(Collections.emptyList());
                map().setEmployers(Collections.emptyList());
            }
        });

        modelMapper.addMappings(new PropertyMap<Account, AccountResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setNumber(source.getNumber());
                map().setCurrency(source.getCurrency());
                map().setBalance(source.getBalance());
                map().setCustomerId(source.getCustomer().getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Employer, EmployerResponse>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setName(source.getName());
                map().setAddress(source.getAddress());

                map().setCustomers(Collections.emptyList());
            }
        });

        modelMapper.validate();

        return modelMapper;
    }
}
