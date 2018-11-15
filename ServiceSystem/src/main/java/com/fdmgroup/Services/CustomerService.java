package com.fdmgroup.Services;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

public class CustomerService {
	
	private CustomerDao cd;
	
	public CustomerService() {
		super();
		cd = new CustomerDao(Persistence.createEntityManagerFactory("EMF"));
	}

	public boolean register(String username, String email, String password) {
		boolean result = false;
		if(cd.getByUsernameOrEmail(username, email).size() < 1) {
			Calendar calendar = Calendar.getInstance();
			Customer customer = new Customer(username, password, email, "active", calendar, calendar, calendar);
			cd.add(customer);
			result = true;
		}
		return result;
	}
}
