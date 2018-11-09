package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Customer;

import static org.mockito.Mockito.*;

public class CustomerDaoTest {
	@Test
	public void Given_CustomerDao_When_getCustomer_returnCustomerAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		CustomerDao customerDao = new CustomerDao(EMF);

		when(EMF.createEntityManager()).thenReturn(EM);

		long customerId = 123;
		customerDao.get(customerId);

		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Customer.class, customerId);
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
		long customerId = 123;
		when(EM.find(Customer.class, customerId)).thenReturn(customer);

		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.update(customerId, customer);

		InOrder order = inOrder(EMF, EM, ET, customer);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Customer.class, customerId);
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
		long customerId = 123;
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Customer.class, customerId)).thenReturn(customer);

		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.delete(customerId);

		InOrder order = inOrder(EMF, EM, ET);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Customer.class, customerId);
		order.verify(EM).remove(customer);
		order.verify(ET).commit();
		order.verify(EM).close();
	}

	@Test
	public void Given_CustomerDao_When_getCustomerByUsernameAndPassword_returnCustomerAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		CustomerDao customerDao = new CustomerDao(EMF);
		TypedQuery<Customer> query = mock(TypedQuery.class);
		String queryStr = "SELECT c FROM Customer c WHERE c.username=:username and c.password=:password";

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.createQuery(queryStr, Customer.class)).thenReturn(query);

		customerDao.getByUsernameAndPassword("robertwang", "rw1234");

		InOrder order = inOrder(EMF, EM, query);
		order.verify(EMF).createEntityManager();
		order.verify(EM).createQuery(queryStr, Customer.class);
		order.verify(query).setParameter("username", "robertwang");
		order.verify(query).setParameter("password", "rw1234");
		order.verify(query).getResultList();
		order.verify(EM).close();

	}
	
	@Test
	public void Given_CustomerDao_When_getByUsernameOrEmail_returnCustomerAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		CustomerDao customerDao = new CustomerDao(EMF);
		TypedQuery<Customer> query = mock(TypedQuery.class);
		String queryStr = "SELECT c FROM Customer c WHERE c.username=:username or c.email=:email";

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.createQuery(queryStr, Customer.class)).thenReturn(query);

		customerDao.getByUsernameOrEmail("robertwang", "robert.wang@fdmgroup.com");

		InOrder order = inOrder(EMF, EM, query);
		order.verify(EMF).createEntityManager();
		order.verify(EM).createQuery(queryStr, Customer.class);
		order.verify(query).setParameter("username", "robertwang");
		order.verify(query).setParameter("email", "robert.wang@fdmgroup.com");
		order.verify(query).getResultList();
		order.verify(EM).close();

	}
}
