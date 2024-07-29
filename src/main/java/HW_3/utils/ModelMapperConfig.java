package HW_3.utils;

import HW_3.account.AccountResponse;
import HW_3.customer.Customer;
import HW_3.customer.CustomerResponse;
import HW_3.employer.EmployerResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.stream.Collectors;

public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Customer, CustomerResponse>() {
            @Override
            protected void configure() {
                if (source.getAccounts() != null) {
                    map().setAccounts(source.getAccounts().stream()
                            .map(account -> modelMapper.map(account, AccountResponse.class))
                            .collect(Collectors.toList()));
                } else {
                    map().setAccounts(Collections.emptyList());
                }

                if (source.getEmployers() != null) {
                    map().setEmployers(source.getEmployers().stream()
                            .map(employer -> modelMapper.map(employer, EmployerResponse.class))
                            .collect(Collectors.toList()));
                } else {
                    map().setEmployers(Collections.emptyList());
                }
            }
        });

        // Другие маппинги можно добавить здесь

        return modelMapper;
    }
}
