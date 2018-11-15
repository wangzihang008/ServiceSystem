package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.Entity.Customer;

public class LoginControllerTest {
	
	@Test
	public void When_goToLogin_Then_returnLoginJspPage() {
		LoginController lc = new LoginController();
		Model model = mock(Model.class);
		Customer customer = mock(Customer.class);
		HttpSession hs = mock(HttpSession.class);
		String nextPage = lc.goToLogin(model, customer, hs);
		
		assertEquals("login", nextPage);
	}
}
