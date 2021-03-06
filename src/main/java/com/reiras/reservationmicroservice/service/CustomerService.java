package com.reiras.reservationmicroservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reiras.reservationmicroservice.domain.Customer;
import com.reiras.reservationmicroservice.exception.ObjectNotFoundException;
import com.reiras.reservationmicroservice.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findById(String id) {
		if (id == null || id.isBlank())
			throw new ObjectNotFoundException(
					"Object not found! Id is invalid: " + id + ", Type: " + Customer.class.getName());

		Optional<Customer> obj = customerRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Customer.class.getName()));
	}

	public Customer update(Customer obj) {
		this.findById(obj.getId());
		return customerRepository.save(obj);
	}
}
