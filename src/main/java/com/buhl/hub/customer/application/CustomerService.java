package com.buhl.hub.customer.application;

import com.buhl.hub.customer.values.CustomerDTO;

import java.util.Collection;

public interface CustomerService {

    Collection<CustomerDTO> getAll();

}
