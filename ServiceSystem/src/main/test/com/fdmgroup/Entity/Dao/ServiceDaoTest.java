package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entity.Service;

public class ServiceDaoTest {
	@Mock // same as = mock(EntityManagerFactory.class)
	private EntityManagerFactory EMF;
	@InjectMocks
	private ServiceDao serviceDao = new ServiceDao();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_ServiceDao_When_getService_returnServiceAndCleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
//		ServiceDao serviceDao = new ServiceDao(EMF);
		long serviceId = 123;
		serviceDao.get(serviceId);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Service.class, serviceId);
		order.verify(EM).close();
	}
	
	@Test
	public void Given_ServiceDao_When_addService_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Service service = mock(Service.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
//		ServiceDao serviceDao = new ServiceDao(EMF);
		serviceDao.add(service);
		
		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).persist(service);
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_ServiceDao_When_updateService_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Service service = mock(Service.class);
//		Vendor oldVendor = mock(Vendor.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long serviceId = 123;
		when(EM.find(Service.class, serviceId)).thenReturn(service);
		
//		ServiceDao serviceDao = new ServiceDao(EMF);
		serviceDao.update(serviceId, service);
		
		InOrder order = inOrder(EMF, EM, ET, service);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(service).setCurrency(service.getCurrency());
		order.verify(service).setName(service.getName());
		order.verify(service).setPer(service.getPer());
		order.verify(service).setPrice(service.getPrice());
		order.verify(service).setStatus(service.getStatus());
		order.verify(service).setVendor(service.getVendor());
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_ServiceDao_When_deleteService_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Service service = mock(Service.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long serviceId = 123;
		when(EM.find(Service.class, serviceId)).thenReturn(service);
		
//		ServiceDao serviceDao = new ServiceDao(EMF);
		serviceDao.delete(serviceId);
		
		InOrder order = inOrder(EMF, EM, ET, service);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Service.class, serviceId);
		order.verify(service).setStatus("inactive");
		order.verify(ET).commit();
		order.verify(EM).close();
	}
}
