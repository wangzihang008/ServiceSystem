package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Customer;

public class CustomerDao {
	private EntityManagerFactory emf;
	
	public CustomerDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	/**
	 * 
	 * @param id
	 * @return Customer object with the id
	 */
	public Customer get(int id) {
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
	public void update(int id, Customer customer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Customer old = em.find(Customer.class, id);
		old.setEmail(customer.getEmail());
		old.setUsername(customer.getUsername());
		old.setPassword(customer.getPassword());
		old.setStatus(customer.getStatus());
		old.setLast_updated_time(customer.getLast_updated_time());
		old.setLast_log_date_time(customer.getLast_log_date_time());
		old.setCreate_date_time(customer.getCreate_date_time());
		et.commit();
		em.close();
	}

	/**
	 * delete record by id
	 * @param id
	 */
	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Customer customer = em.find(Customer.class, id);
		em.remove(customer);
		et.commit();
		em.close();
		
	}
}
