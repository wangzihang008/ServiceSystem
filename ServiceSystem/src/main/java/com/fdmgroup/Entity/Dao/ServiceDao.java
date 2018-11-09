package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Order;
import com.fdmgroup.Entity.Service;

public class ServiceDao {

	private EntityManagerFactory emf;

	public ServiceDao(EntityManagerFactory emf) {
		this.emf = emf;
		// TODO Auto-generated constructor stub
	}

	public Service get(long id) {
		EntityManager em = emf.createEntityManager();
		Service service = em.find(Service.class, id);
		em.close();
		return service;
		
	}

	public void update(long id, Service service) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Service old = em.find(Service.class, id);
		old.setCurrency(service.getCurrency());
		old.setName(service.getName());
		old.setPer(service.getPer());
		old.setPrice(service.getPrice());
		old.setStatus(service.getStatus());
		old.setVendor(service.getVendor());
		et.commit();
		em.close();
		
	}

	public void add(Service service) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(service);
		et.commit();
		em.close();
		
	}

	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Service service = em.find(Service.class, id);
		em.remove(service);
		et.commit();
		em.close();
		
	}

	
	
}
