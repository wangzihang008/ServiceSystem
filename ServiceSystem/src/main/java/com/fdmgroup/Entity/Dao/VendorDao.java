package com.fdmgroup.Entity.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fdmgroup.Entity.Vendor;

public class VendorDao {
	@Resource(name="emfBean")
	private EntityManagerFactory emf;

	public VendorDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public VendorDao() {
		super();
	}

	/**
	 * 
	 * @param long
	 * @return vendor by id
	 */
	public Vendor get(long id) {
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
	public void update(long id, Vendor vendor) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Vendor old = em.find(Vendor.class, id);
		old.setAddress(vendor.getAddress());
		old.setPhoneNumber(vendor.getPhoneNumber());
		old.setStatus(vendor.getStatus());
		old.setVerification(vendor.getVerification());
		old.setStoreName(vendor.getStoreName());
		old.setStoreUrl(vendor.getStoreUrl());
		old.setVendorFirstName(vendor.getVendorFirstName());
		old.setVendorLastName(vendor.getVendorLastName());
		old.setVendorMiddleName(vendor.getVendorMiddleName());
		old.setCustomer(vendor.getCustomer());
		et.commit();
		em.close();
		
	}

	/**
	 * set status of a record of vendor table to "inactive" by id
	 * @param id
	 */
	public void delete(long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		Vendor vendor = em.find(Vendor.class, id);
		vendor.setStatus("inactive");
		et.commit();
		em.close();
	}

	public ArrayList<Vendor> getAllVendor() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		String strQuery = "select v from vendor v";
		
		TypedQuery<Vendor> query = (TypedQuery<Vendor>) em.createQuery(strQuery);
		ArrayList<Vendor> result = (ArrayList<Vendor>) query.getResultList();
		em.close();
		return result;
	}

	
}
