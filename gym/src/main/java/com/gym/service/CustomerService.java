package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.model.Customer;
import com.gym.model.MemberShip;
import com.gym.repos.CustomerRepository;
import com.gym.repos.MemberShipRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

	
	@Autowired
	private MemberShipRepository repo1;

	@Autowired
	private CustomerRepository customerrepo;
	
	public void saveCustomer(Customer cus) {

		customerrepo.save(cus);
	}

	public List<Customer> viewCustomer() {
		return customerrepo.findAll();
		
	}
	public Customer viewCustomerById( int id) {
		return customerrepo.findById(id).get();
		
	}
	@Transactional
	public void deleteCustomer(int cusId) {
	    customerrepo.deleteById(cusId);
	}

	
	
	public void updateCustomer(Customer cus) {
		customerrepo.save(cus);
		
	}

	
	public List<MemberShip> viewmembers() {

	 return repo1.findAll();

	}
}
