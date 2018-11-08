package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

import static org.mockito.Mockito.*;

public class CustomerDaoTest {
	@Test
	public void Given_CustomerDao_When_getCustomer_returnCustomerAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		CustomerDao customerDao = new CustomerDao(EMF);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
		customerDao.get(123);
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Customer.class, 123);
		order.verify(EM).close();
		
	}
	
	@Test
	public void Given_CustomerDao_When_addCustomer_CleansUpReources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		
		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.add(customer);
		
		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).persist(customer);
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_CustomerDao_When_updateCustomer_CleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Customer.class, 123)).thenReturn(customer);
		
		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.update(123, customer);
		
		InOrder order = inOrder(EMF, EM, ET, customer);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Customer.class, 123);
		order.verify(customer).setEmail(customer.getEmail());
		order.verify(customer).setUsername(customer.getUsername());
		order.verify(customer).setPassword(customer.getPassword());
		order.verify(customer).setStatus(customer.getStatus());
		order.verify(customer).setLast_updated_time(customer.getLast_updated_time());
		order.verify(customer).setLast_log_date_time(customer.getLast_log_date_time());
		order.verify(customer).setCreate_date_time(customer.getCreate_date_time());
		order.verify(ET).commit();
		order.verify(EM).close();
	}
	
	@Test
	public void Given_CustomerDao_When_deleteCustomer_CleansUpReources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Customer.class, 123)).thenReturn(customer);
		
		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.delete(123);
		
		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Customer.class, 123);
		order.verify(EM).remove(customer);
		order.verify(ET).commit();
		order.verify(EM).close();
	}
}
