package com.fdmgroup.Entity.Dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entity.Customer;

public class CustomerDao {
	@Resource(name="emfBean")
	private EntityManagerFactory emf;
	@Resource(name="calendarBean")
	private Calendar calendar;
	
	public CustomerDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	public CustomerDao() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return Customer object with the id
	 */
	public Customer get(long id) {
		EntityManager em = emf.createEntityManager();
		Customer customer = em.find(Customer.class, id);
		em.close();
		return customer;
	}
	
	/**
	 * insert 1 customer record into database
	 * @param customer
	 */
	public void add(Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(customer);
		et.commit();
		em.close();
	}
	
	/**
	 * update record by given id with given customer object
	 * @param id
	 * @param customer
	 */
	public void update(long id, Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Customer old = em.find(Customer.class, id);
		old.setEmail(customer.getEmail());
		old.setUsername(customer.getUsername());
		old.setPassword(customer.getPassword());
		old.setStatus(customer.getStatus());
		old.setLastUpdatedTime(customer.getLastUpdatedTime());
		old.setLastLogDateTime(customer.getLastLogDateTime());
		old.setCreateDateTime(customer.getCreateDateTime());
		old.setVendor(customer.getVendor());
		et.commit();
		em.close();
	}

	/**
	 * delete record by id
	 * @param id
	 */
	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Customer customer = em.find(Customer.class, id);
		customer.setStatus("inactive");
		et.commit();
		em.close();
		
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return Customer who hold username and password, otherwise return null
	 */
	public Customer getByUsernameAndPassword(String username, String password) {
		EntityManager em = emf.createEntityManager();
		Customer result = null;
		String queryStr = "SELECT c FROM Customer c WHERE c.username=:username and "
				+ "c.password=:password";
		TypedQuery<Customer> query = (TypedQuery<Customer>) em.createQuery(queryStr, Customer.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		List<Customer> resultList = (List<Customer>) query.getResultList();
		if(resultList.size() == 1) {
			result = resultList.get(0);
		}
		em.close();
		return result;
	}
	
	/**
	 * 
	 * @param username
	 * @param email
	 * @return Customers who hold the username or email
	 */
	public List<Customer> getByUsernameOrEmail(String username, String email) {
		EntityManager em = emf.createEntityManager();
		String queryStr = "SELECT c FROM Customer c WHERE c.username=:username or "
				+ "c.email=:email";
		TypedQuery<Customer> query = (TypedQuery<Customer>) em.createQuery(queryStr, Customer.class);
		query.setParameter("username", username);
		query.setParameter("email", email);
		
		List<Customer> resultList = query.getResultList();
		em.close();
		return resultList;
	}
	
	public void createCustomerThenAdd(Customer customer) {
//		Calendar calendar = Calendar.getInstance();
		String str = "active";
		customer.setLastLogDateTime(calendar);
		customer.setLastUpdatedTime(calendar);
		customer.setCreateDateTime(calendar);
		customer.setStatus(str);
		add(customer);
	}
}
