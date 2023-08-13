package com.mindtree.controller;

import com.mindtree.dto.CustomerDto;
import com.mindtree.exception.CustomerNotFoundException;
import com.mindtree.model.Customer;
import com.mindtree.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestHeader String authorization, @RequestBody Customer customer, final HttpServletRequest request) {
        
        return  new ResponseEntity<Customer>(customerService.registerUser(customer),HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestHeader String authorization, @PathVariable("customerId") Long customerId, @RequestBody CustomerDto customerDto) throws CustomerNotFoundException{
        Customer updatedCustomer = customerService.updateCustomer(customerId,customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader String authorization, @PathVariable("customerId") Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body("Customer is deleted successfully with id : "+customerId);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader String authorization){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
    }
}
