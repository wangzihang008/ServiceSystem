package com.fdmgroup.Entity.Dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Order;
import com.fdmgroup.Entity.Service;

public class ServiceDao {
	@Resource(name="emfBean")
	private EntityManagerFactory emf;

	public ServiceDao(EntityManagerFactory emf) {
		this.emf = emf;
		// TODO Auto-generated constructor stub
	}

	public ServiceDao() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @return a record of service table by id
	 */
	public Service get(long id) {
		EntityManager em = emf.createEntityManager();
		Service service = em.find(Service.class, id);
		em.close();
		return service;
		
	}
	
	/**
	 * update the record of service by id and data of object of service
	 * @param id
	 * @param service
	 */
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

	/**
	 * insert a record of service table 
	 * @param service
	 */
	public void add(Service service) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(service);
		et.commit();
		em.close();
		
	}

	/**
	 * set status of a record to "inactive" by id
	 * @param id
	 */
	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Service service = em.find(Service.class, id);
		service.setStatus("inactive");
		et.commit();
		em.close();
		
	}

	
	
}
