package com.fdmgroup.Entity.Dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Order;

public class OrderDao {
	@Resource(name="emfBean")
	private EntityManagerFactory emf;

	public OrderDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public OrderDao() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return order with id
	 */
	public Order get(long id) {
		EntityManager em = emf.createEntityManager();
		Order order = em.find(Order.class, id);
		em.close();
		return order;
	}

	/**
	 * insert an order record into order table
	 * @param order
	 */
	public void add(Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(order);
		et.commit();
		em.close();
	}
	
	/**
	 * update record with id and data of object of order
	 * @param id
	 * @param order
	 */
	public void update(long id, Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Order old = em.find(Order.class, id);
		old.setAppointmentDateTime(order.getAppointmentDateTime());
		old.setLastUpdatedDateTime(order.getLastUpdatedDateTime());
		old.setStatus(order.getStatus());
		old.setCustomer(order.getCustomer());
		old.setService(order.getService());
		et.commit();
		em.close();
	}

	/**
	 * set a record as "inactive"
	 * @param id
	 */
	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Order order = em.find(Order.class, id);
		order.setStatus("inactive");
		et.commit();
		em.close();
	}
}
