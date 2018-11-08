package com.fdmgroup.Entity.Dao;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Test;
import org.mockito.InOrder;

import com.fdmgroup.Entity.Customer;

public class AdministratorDaoTest {
	@Test
	public void Given_AdministratorDao_WhenGetAdministratorUsernameIsCorrectAndPasswordIsCorrect_returnAdministratorAndCleansUpResources() {
		EntityManagerFactory EMF = mock(EntityManagerFactory.class);
		EntityManager EM = mock(EntityManager.class);
		AdministratorDao administratorDao = new AdministratorDao(EMF);
		
		when(EMF.createEntityManager()).thenReturn(EM);
		
		administratorDao.getAdministrator("robert", "123");
		
		InOrder order = inOrder(EMF, EM);
		order.verify(EMF).createEntityManager();
		order.verify(EM).find(Customer.class, 123);
		order.verify(EM).close();
	}
}
