package hha.spring.data.dataapi.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import hha.spring.data.dataapi.domain.Customer;
import hha.spring.data.dataapi.repository.CustomerRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	public void saveCustomer(Customer customer) {
		customerRepo.save(customer);
	}
}
