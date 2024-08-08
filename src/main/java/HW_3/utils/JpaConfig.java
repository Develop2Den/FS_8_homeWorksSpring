package HW_3.utils;

import HW_3.account.db.Account;
import HW_3.account.api.dto.AccountResponse;
import HW_3.customer.db.Customer;
import HW_3.customer.api.dto.CustomerResponse;
import HW_3.employer.db.Employer;
import HW_3.employer.api.dto.EmployerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Collections;


@Configuration
@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class JpaConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

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
            }
        });

        modelMapper.validate();

        return modelMapper;
    }
}
