package com.fdmgroup.Entity.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

import static org.mockito.Mockito.*;

import java.util.Calendar;

public class CustomerDaoTest {
	@Mock // same as = mock(EntityManagerFactory.class)
	private EntityManagerFactory EMF;
	@Mock // same as = mock(EntityManagerFactory.class)
	private Calendar calendar;
	@InjectMocks
	private CustomerDao customerDao = new CustomerDao();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Given_CustomerDao_When_getCustomer_returnCustomerAndCleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
//		CustomerDao customerDao = new CustomerDao(EMF);

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
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);

//		CustomerDao customerDao = new CustomerDao(EMF);
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
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		long customerId = 123;
		when(EM.find(Customer.class, customerId)).thenReturn(customer);

//		CustomerDao customerDao = new CustomerDao(EMF);
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
		order.verify(customer).setLastUpdatedTime(customer.getLastUpdatedTime());
		order.verify(customer).setLastLogDateTime(customer.getLastLogDateTime());
		order.verify(customer).setCreateDateTime(customer.getCreateDateTime());
		order.verify(ET).commit();
		order.verify(EM).close();
	}

	@Test
	public void Given_CustomerDao_When_deleteCustomer_CleansUpReources() {
		long customerId = 123;
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);

		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		when(EM.find(Customer.class, customerId)).thenReturn(customer);

//		CustomerDao customerDao = new CustomerDao(EMF);
		customerDao.delete(customerId);

		InOrder order = inOrder(EMF, EM, ET, customer);
		order.verify(EMF).createEntityManager();
		order.verify(EM).getTransaction();
		order.verify(ET).begin();
		order.verify(EM).find(Customer.class, customerId);
		order.verify(customer).setStatus("inactive");
		order.verify(ET).commit();
		order.verify(EM).close();
	}

	@Test
	public void Given_CustomerDao_When_getCustomerByUsernameAndPassword_returnCustomerAndCleansUpResources() {
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
//		CustomerDao customerDao = new CustomerDao(EMF);
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
//		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
//		CustomerDao customerDao = new CustomerDao(EMF);
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
	
	@Test
	public void Given_CustomerDao_When_createCustomerAndAdd_Then_callAddAndCleansUpResources() {
		EntityManager EM = mock(EntityManager.class);
		EntityTransaction ET = mock(EntityTransaction.class);
		Customer customer = mock(Customer.class);
		String str = "active";
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.getTransaction()).thenReturn(ET);
		customerDao.createCustomerThenAdd(customer);
		
		InOrder order = inOrder(customer);
		order.verify(customer).setLastLogDateTime(calendar);
		order.verify(customer).setLastUpdatedTime(calendar);
		order.verify(customer).setCreateDateTime(calendar);
		order.verify(customer).setStatus(str);
	}
	
}
