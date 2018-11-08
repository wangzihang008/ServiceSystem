package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Order;

public class OrderDaoTest {
	@Test
	public void Given_OrderDao_When_getOrder_returnOrderAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
		OrderDao orderDao = new OrderDao(EMF);
		orderDao.get(123);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Order.class, 123);
		order.verify(EM).close();
	}
	
	@Test
	public void Given_OrderDao_When_addOrder_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
		OrderDao orderDao = new OrderDao(EMF);
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
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Order.class, 123)).thenReturn(order);
		
		OrderDao orderDao = new OrderDao(EMF);
		orderDao.update(123, order);
		
		InOrder inorder = inOrder(EMF, EM, ET, order);
		inorder.verify(EMF).createEntityManager();
		inorder.verify(EM).getTransaction();
		inorder.verify(ET).begin();
		inorder.verify(order).setAppointment_date_time(order.getAppointment_date_time());
		inorder.verify(order).setLast_updated_date_time(order.getLast_updated_date_time());
		inorder.verify(order).setStatus(order.getStatus());
		inorder.verify(order).setCustomer(order.getCustomer());
		inorder.verify(order).setService(order.getService());
		inorder.verify(ET).commit();
		inorder.verify(EM).close();
	}
	
	@Test
	public void Given_OrderDao_When_deleteOrder_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Order order = mock(Order.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Order.class, 123)).thenReturn(order);
		
		OrderDao orderDao = new OrderDao(EMF);
		orderDao.delete(123);
		
		InOrder inorder = inOrder(EMF, EM, ET);
		inorder.verify(EMF).createEntityManager();
		inorder.verify(EM).getTransaction();
		inorder.verify(ET).begin();
		inorder.verify(EM).find(Order.class, 123);
		inorder.verify(EM).remove(order);
		inorder.verify(ET).commit();
		inorder.verify(EM).close();
	}
}
