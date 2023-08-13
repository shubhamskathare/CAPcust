package com.mindtree.service;

import com.mindtree.dto.CustomerDto;
import com.mindtree.exception.CustomerNotFoundException;
import com.mindtree.model.Customer;
import com.mindtree.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    

    @Override
    public Customer updateCustomer(Long customerId, CustomerDto customerDto) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->  new CustomerNotFoundException("Customer not found with Id : "+customerId));
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException{
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->  new CustomerNotFoundException("Customer not found with Id : "+customerId));
        customerRepository.deleteById(customer.getCustomerId());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

	@Override
	public Customer registerUser(Customer customer) {
		
		return customerRepository.save(customer);
	}
}
