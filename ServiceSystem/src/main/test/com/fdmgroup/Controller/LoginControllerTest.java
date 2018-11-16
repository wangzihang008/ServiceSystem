package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

public class LoginControllerTest {
	@Mock
	private CustomerDao cDao;
	@InjectMocks
	private LoginController lc = new LoginController();
	
	@Before
	public void startInjectionMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void When_goToLogin_Then_returnLoginJspPage() {
		Model model = mock(Model.class);
		Customer customer = mock(Customer.class);
		HttpSession hs = mock(HttpSession.class);
		String nextPage = lc.goToLogin(model, customer, hs);
		
		assertEquals("login", nextPage);
	}
	
	@Test
	public void When_goToLogin_Given_customerUsernameEmpty_Then_returnLoginJspPage() {
		LoginController lc = new LoginController();
		Model model = mock(Model.class);
		Customer customer = new Customer();
		customer.setUsername("");
		HttpSession hs = mock(HttpSession.class);
		String nextPage = lc.goToLogin(model, customer, hs);
		
		assertEquals("login", nextPage);
	}
	
	@Test
	public void When_goToLogin_Given_customerUsernameNotEmpty_Then_returnLoginJspPage() {
		Model model = mock(Model.class);
		Customer customer = new Customer();
		customer.setUsername("robert");
		customer.setUsername("robert");
		HttpSession hs = mock(HttpSession.class);
		
		when(cDao.getByUsernameAndPassword(customer.getUsername(), customer.getPassword())).thenReturn(customer);
		
		String nextPage = lc.goToLogin(model, customer, hs);
		
		assertEquals("login", nextPage);
	}
}
