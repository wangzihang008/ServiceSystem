package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import static org.mockito.Mockito.*;

import com.fdmgroup.Entity.Customer;

public class RegisterControllerTest {
	@Test
	public void when_goToRegister_then_returnRegisterJspPageName() {
		RegisterController rc = new RegisterController();
		Customer customer = new Customer();
		HttpSession hs = mock(HttpSession.class);
		String nextPage = rc.goToRegister(customer, hs);
		
		assertEquals("register", nextPage);
	}
}
