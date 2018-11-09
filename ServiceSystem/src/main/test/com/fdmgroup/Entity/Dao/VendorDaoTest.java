package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Vendor;

public class VendorDaoTest {
	@Test
	public void Given_VendorDao_When_getVendor_returnVendorAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
		VendorDao vendorDao = new VendorDao(EMF);
		long vendorId = 123;
		vendorDao.get(vendorId);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Vendor.class, vendorId);
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_When_addVendor_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
		VendorDao vendorDao = new VendorDao(EMF);
		vendorDao.add(vendor);
		
		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).persist(vendor);
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_When_updateVendor_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
//		Vendor oldVendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long vendorId = 123;
		when(EM.find(Vendor.class, vendorId)).thenReturn(vendor);
		
		VendorDao vendorDao = new VendorDao(EMF);
		vendorDao.update(vendorId, vendor);
		
		InOrder order = inOrder(EMF, EM, ET, vendor);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(vendor).setAddress(vendor.getAddress());
		order.verify(vendor).setPhone_number(vendor.getPhone_number());
		order.verify(vendor).setStatus(vendor.getStatus());
		order.verify(vendor).setVerification(vendor.getVerification());
		order.verify(vendor).setStore_name(vendor.getStore_name());
		order.verify(vendor).setStore_url(vendor.getStore_url());
		order.verify(vendor).setVendor_first_name(vendor.getVendor_first_name());
		order.verify(vendor).setVendor_last_name(vendor.getVendor_last_name());
		order.verify(vendor).setVendor_middle_name(vendor.getVendor_middle_name());
		order.verify(vendor).setCustomer(vendor.getCustomer());
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_When_deleteVendor_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long vendorId = 123;
		when(EM.find(Vendor.class, vendorId)).thenReturn(vendor);
		
		VendorDao vendorDao = new VendorDao(EMF);
		vendorDao.delete(vendorId);
		
		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Vendor.class, vendorId);
		order.verify(EM).remove(vendor);
		order.verify(ET).commit();
		order.verify(EM).close();
	}
}
