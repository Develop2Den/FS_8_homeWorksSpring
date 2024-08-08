package HW_4.customer;

import HW_4.account.api.dto.AccountRequest;
import HW_4.account.api.dto.AccountResponse;
import HW_4.customer.api.CustomerController;
import HW_4.customer.api.dto.CustomerRequest;
import HW_4.customer.api.dto.CustomerResponse;
import HW_4.customer.db.Customer;
import HW_4.customer.service.CustomerFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static HW_4.enums.Currency.USD;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerFacade customerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("John Doe");
        customerRequest.setEmail("john.doe@example.com");
        customerRequest.setAge(30);
        customerRequest.setPhoneNumber("1234567890");

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setName("John Doe");
        customerResponse.setEmail("john.doe@example.com");
        customerResponse.setAge(30);
        customerResponse.setPhoneNumber("1234567890");

        when(customerFacade.createCustomer(any())).thenReturn(customerResponse);

        mockMvc.perform(post("/api/customers")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("John Doe Updated");
        customerRequest.setEmail("john.doe.updated@example.com");
        customerRequest.setAge(31);
        customerRequest.setPhoneNumber("7654321");

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setName("John Doe Updated");
        customerResponse.setEmail("john.doe.updated@example.com");
        customerResponse.setAge(31);
        customerResponse.setPhoneNumber("7654321");

        when(customerFacade.updateCustomer(anyLong(), any())).thenReturn(customerResponse);

        mockMvc.perform(put("/api/customers/1")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(customerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe Updated"))
                .andExpect(jsonPath("$.email").value("john.doe.updated@example.com"))
                .andExpect(jsonPath("$.age").value(31))
                .andExpect(jsonPath("$.phoneNumber").value("7654321"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        when(customerFacade.deleteCustomer(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testGetCustomer() throws Exception {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        customerResponse.setName("John Doe");
        customerResponse.setEmail("john.doe@example.com");
        customerResponse.setAge(30);
        customerResponse.setPhoneNumber("1234567890");

        when(customerFacade.getCustomer(anyLong())).thenReturn(customerResponse);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    void testCreateAccountForCustomer() throws Exception {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setCurrency(USD);
        accountRequest.setBalance(100.0);

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(1L);
        accountResponse.setNumber("12345");
        accountResponse.setCurrency(USD);
        accountResponse.setBalance(100.0);

        when(customerFacade.createAccountForCustomer(anyLong(), any())).thenReturn(accountResponse);

        mockMvc.perform(post("/api/customers/1/accounts")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(accountRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.number").value("12345"))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.balance").value(100.0));
    }

    @Test
    void testDeleteAccountFromCustomer() throws Exception {
        when(customerFacade.deleteAccountFromCustomer(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/customers/1/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}



