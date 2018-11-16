package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDaoTest;
import com.fdmgroup.Entity.Dao.CustomerDao;

public class RegisterControllerTest {
	@Mock
	private CustomerDao cDao;
	@InjectMocks
	private RegisterController rc = new RegisterController();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void when_goToRegister_given_emptyEmail_then_returnRegisterJspPageName() {
		Customer customer = new Customer();
		customer.setEmail("");
		HttpSession hs = mock(HttpSession.class);
		String nextPage = rc.goToRegister(customer, hs);
		
		assertEquals("register", nextPage);
	}
	
	@Test
	public void when_goToRegister_then_returnRegisterJspPageName() {
		RegisterController rc = new RegisterController();
		Customer customer = new Customer();
		customer.setEmail("robert");
		customer.setUsername("robert");
		customer.setPassword("robert");
		HttpSession hs = mock(HttpSession.class);
		
		ArrayList<Customer> result = new ArrayList<Customer>();
		
		when(cDao.getByUsernameOrEmail(customer.getUsername(), customer.getEmail())).thenReturn(result);

		String nextPage = rc.goToRegister(customer, hs);
		
		assertEquals("dashboard", nextPage);
	}
	
	@Test
	public void when_goToRegister_given_someCustomerInDatabase_then_returnRegisterJspPageName() {
		Customer customer = new Customer();
		customer.setEmail("robert");
		customer.setUsername("robert");
		customer.setPassword("robert");
		HttpSession hs = mock(HttpSession.class);
		
		Customer c = new Customer();
		ArrayList<Customer> result = new ArrayList<Customer>();
		result.add(c);
		
		when(cDao.getByUsernameOrEmail(customer.getUsername(), customer.getEmail())).thenReturn(result);

		String nextPage = rc.goToRegister(customer, hs);
		
		assertEquals("register", nextPage);
	}
	
	@Test
	public void when_goToRegister_given_model_then_returnRegisterJspPageName() {
		Model model = mock(Model.class);
		String nextPage = rc.goToRegister(model);
		
		assertEquals("register", nextPage);
	}
}
