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

import com.fdmgroup.Entity.Order;

public class OrderDaoTest {
	@Mock // same as = mock(EntityManagerFactory.class)
	private EntityManagerFactory EMF;
	@InjectMocks
	private OrderDao orderDao = new OrderDao();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_OrderDao_When_getOrder_returnOrderAndCleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
//		OrderDao orderDao = new OrderDao(EMF);
		long orderId = 123;
		orderDao.get(orderId);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Order.class, orderId);
		order.verify(EM).close();
	}
	
	@Test
	public void Given_OrderDao_When_addOrder_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
//		OrderDao orderDao = new OrderDao(EMF);
		orderDao.add(order);
		
		InOrder inorder = inOrder(EMF, EM, ET);
		inorder.verify(EMF).createEntityManager();
		inorder.verify(EM).getTransaction();
		inorder.verify(ET).begin();
		inorder.verify(EM).persist(order);
		inorder.verify(ET).commit();
		inorder.verify(EM).close();
	}
	
	@Test
	public void Given_OrderDao_When_updateOrder_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long orderId = 123;
		when(EM.find(Order.class, orderId)).thenReturn(order);
		
//		OrderDao orderDao = new OrderDao(EMF);
		orderDao.update(orderId, order);
		
		InOrder inorder = inOrder(EMF, EM, ET, order);
		inorder.verify(EMF).createEntityManager();
		inorder.verify(EM).getTransaction();
		inorder.verify(ET).begin();
		inorder.verify(order).setAppointmentDateTime(order.getAppointmentDateTime());
		inorder.verify(order).setLastUpdatedDateTime(order.getLastUpdatedDateTime());
		inorder.verify(order).setStatus(order.getStatus());
		inorder.verify(order).setCustomer(order.getCustomer());
		inorder.verify(order).setService(order.getService());
		inorder.verify(ET).commit();
		inorder.verify(EM).close();
	}
	
	@Test
	public void Given_OrderDao_When_deleteOrder_CleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long orderId = 123;
		when(EM.find(Order.class, orderId)).thenReturn(order);
		
//		OrderDao orderDao = new OrderDao(EMF);
		orderDao.delete(orderId);
		
		InOrder inorder = inOrder(EMF, EM, ET, order);
		inorder.verify(EMF).createEntityManager();
		inorder.verify(EM).getTransaction();
		inorder.verify(ET).begin();
		inorder.verify(EM).find(Order.class, orderId);
		inorder.verify(order).setStatus("inactive");
		inorder.verify(ET).commit();
		inorder.verify(EM).close();
	}
}
