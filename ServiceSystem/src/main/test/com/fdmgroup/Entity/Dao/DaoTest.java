package com.fdmgroup.Entity.Dao;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Customer;

public class DaoTest {
	@Test
	public void Given_Dao_WhenExcuteQuery_ReturnListOfObjectsAndCleansUpRecourses() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		TypedQuery<Customer> query = mock(TypedQuery.class);
		String querystr = "select e from customer e";
		List<Customer> customers = new ArrayList<Customer>();
		
		when(EMF.createEntityManager()).thenReturn(EM);
		when(EM.createQuery(querystr, Customer.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(customers);
		
		Dao<Customer> dao = new Dao(EMF);
		dao.excuteQuery(Customer.class, querystr);
		
		InOrder order = inOrder(EMF, EM, query);
		order.verify(EMF).createEntityManager();
		order.verify(EM).createQuery(querystr, Customer.class);
		order.verify(query).getResultList();
		order.verify(EM).close();
	}
}
