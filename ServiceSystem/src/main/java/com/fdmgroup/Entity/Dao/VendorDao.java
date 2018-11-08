package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.fdmgroup.Entity.Vendor;

public class VendorDao {

	private EntityManagerFactory emf;

	public VendorDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * 
	 * @param id
	 * @return vendor by id
	 */
	public Vendor get(int id) {
		EntityManager em = emf.createEntityManager();
		Vendor vendor = em.find(Vendor.class, id);
		em.close();
		return vendor;
	}

	/**
	 * add 1 record of vendor
	 * @param vendor
	 */
	public void add(Vendor vendor) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(vendor);
		et.commit();
		em.close();
		
	}

	/**
	 * update 1 vendor record by id
	 * @param id
	 * @param vendor
	 */
	public void update(int id, Vendor vendor) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Vendor old = em.find(Vendor.class, id);
		old.setAddress(vendor.getAddress());
		old.setPhone_number(vendor.getPhone_number());
		old.setStatus(vendor.getStatus());
		old.setVerification(vendor.getVerification());
		old.setStore_name(vendor.getStore_name());
		old.setStore_url(vendor.getStore_url());
		old.setVendor_first_name(vendor.getVendor_first_name());
		old.setVendor_last_name(vendor.getVendor_last_name());
		old.setVendor_middle_name(vendor.getVendor_middle_name());
		old.setCustomer(vendor.getCustomer());
		et.commit();
		em.close();
		
	}

	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Vendor vendor = em.find(Vendor.class, id);
		em.remove(vendor);
		et.commit();
		em.close();
	}

	
}
