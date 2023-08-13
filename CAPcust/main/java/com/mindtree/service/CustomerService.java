package com.mindtree.service;

import com.mindtree.dto.CustomerDto;
import com.mindtree.exception.CustomerNotFoundException;
import com.mindtree.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer registerUser(Customer customer);

    Customer updateCustomer(Long customerId, CustomerDto customerDto) throws CustomerNotFoundException;

    void deleteCustomer(Long customerId) throws CustomerNotFoundException;

    List<Customer> getAllCustomers();
}
