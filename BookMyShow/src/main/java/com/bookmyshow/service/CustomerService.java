package com.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmyshow.dao.CustomerDao;
import com.bookmyshow.model.Customer;

@Service
public class CustomerService {
 
	@Autowired
	CustomerDao dao;
	public Customer save(Customer customer)
	{
		return dao.save(customer);
	}
	public Customer get(int id)
	{
		return dao.get(id);
	}
	public Customer update(Customer customer)
	{
		return dao.update(customer);
	}
}