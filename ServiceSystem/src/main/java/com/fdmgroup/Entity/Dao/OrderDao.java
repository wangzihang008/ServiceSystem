package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Order;

public class OrderDao {

	private EntityManagerFactory emf;

	public OrderDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public Order get(long id) {
		EntityManager em = emf.createEntityManager();
		Order order = em.find(Order.class, id);
		em.close();
		return order;
	}

	public void add(Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(order);
		et.commit();
		em.close();
	}
	
	public void update(long id, Order order) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Order old = em.find(Order.class, id);
		old.setAppointment_date_time(order.getAppointment_date_time());
		old.setLast_updated_date_time(order.getLast_updated_date_time());
		old.setStatus(order.getStatus());
		old.setCustomer(order.getCustomer());
		old.setService(order.getService());
		et.commit();
		em.close();
	}

	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Order order = em.find(Order.class, id);
		em.remove(order);
		et.commit();
		em.close();
	}
}
