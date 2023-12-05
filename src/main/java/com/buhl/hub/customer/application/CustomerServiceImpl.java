package com.buhl.hub.customer.application;

import com.buhl.hub.customer.domain.CustomerRepository;
import com.buhl.hub.customer.values.CustomerDTO;
import com.buhl.hub.customer.domain.Customer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final JdbcTemplate hubtwoJdbcTemplate;
    @Override
    public Collection<CustomerDTO> getAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false).map(Customer::toDto).toList();
    }

    @PostConstruct
    public void setup() {
        if (!customerRepository.existsById(17L)) {
            hubtwoJdbcTemplate.execute("INSERT INTO Customer(id,name) VALUES(17, 'John Doe')");
        }
    }
}
