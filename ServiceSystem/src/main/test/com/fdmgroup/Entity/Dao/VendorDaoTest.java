package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entity.Vendor;

public class VendorDaoTest {
	@Mock // same as = mock(EntityManagerFactory.class)
	private EntityManagerFactory EMF;
	@InjectMocks
	private VendorDao vendorDao = new VendorDao();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_VendorDao_When_getVendor_returnVendorAndCleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
//		VendorDao vendorDao = new VendorDao(EMF);
		long vendorId = 123;
		vendorDao.get(vendorId);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Vendor.class, vendorId);
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_When_addVendor_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
//		VendorDao vendorDao = new VendorDao(EMF);
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
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
//		Vendor oldVendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long vendorId = 123;
		when(EM.find(Vendor.class, vendorId)).thenReturn(vendor);
		
//		VendorDao vendorDao = new VendorDao(EMF);
		vendorDao.update(vendorId, vendor);
		
		InOrder order = inOrder(EMF, EM, ET, vendor);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(vendor).setAddress(vendor.getAddress());
		order.verify(vendor).setPhoneNumber(vendor.getPhoneNumber());
		order.verify(vendor).setStatus(vendor.getStatus());
		order.verify(vendor).setVerification(vendor.getVerification());
		order.verify(vendor).setStoreName(vendor.getStoreName());
		order.verify(vendor).setStoreUrl(vendor.getStoreUrl());
		order.verify(vendor).setVendorFirstName(vendor.getVendorFirstName());
		order.verify(vendor).setVendorLastName(vendor.getVendorLastName());
		order.verify(vendor).setVendorMiddleName(vendor.getVendorMiddleName());
		order.verify(vendor).setCustomer(vendor.getCustomer());
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_When_deleteVendor_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Vendor vendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long vendorId = 123;
		when(EM.find(Vendor.class, vendorId)).thenReturn(vendor);
		
//		VendorDao vendorDao = new VendorDao(EMF);
		vendorDao.delete(vendorId);
		
		InOrder order = inOrder(EMF, EM, ET, vendor);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Vendor.class, vendorId);
		order.verify(vendor).setStatus("inactive");
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_VendorDao_getAllVendor_Then_returnAllVendorAndCleansUpResources() {
		EntityManager EM = mock(EntityManager.class);
		TypedQuery<Vendor> query = mock(TypedQuery.class);
		ArrayList<Vendor> result = new ArrayList<Vendor>();
		String strQuery = "select v from vendor v";
				
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.createQuery(strQuery)).thenReturn(query);
		when(query.getResultList()).thenReturn(result);
		
		vendorDao.getAllVendor();
		
		InOrder order = inOrder(EMF, EM, query);
		order.verify(EMF).createEntityManager();
		order.verify(EM).createQuery(strQuery);
		order.verify(query).getResultList();
		order.verify(EM).close();
	}
}
