package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.ui.Model;

import com.fdmgroup.Entity.Customer;

public class IndexControllerTest {
	private String activeUserId = "active_userID";

	@Test
	public void given_requestIndexController_when_inactiveUer_then_returnIndexJspName() {
		IndexController ic = new IndexController();
		Model model = mock(Model.class);
		HttpSession hs = mock(HttpSession.class);
		String nextPage = ic.goToIndex(model, hs);

		when(hs.getAttribute(activeUserId)).thenReturn(null);

		InOrder order = inOrder(model, hs);
		order.verify(hs).getAttribute("active_userID");
		order.verify(model).addAttribute("registerCustomer", new Customer());
		order.verify(model).addAttribute("loginCustomer", new Customer());
		assertEquals("index", nextPage);
	}

	@Test
	public void given_requestIndexController_when_activeUser_then_returnIndexJspName() {
		IndexController ic = new IndexController();
		Model model = mock(Model.class);
		HttpSession hs = mock(HttpSession.class);

		when(hs.getAttribute(activeUserId)).thenReturn("1");
		String nextPage = ic.goToIndex(model, hs);

		InOrder order = inOrder(model, hs);
		order.verify(hs).getAttribute("active_userID");
		order.verify(model, never()).addAttribute("registerCustomer", new Customer());
		order.verify(model, never()).addAttribute("loginCustomer", new Customer());
		assertEquals("index", nextPage);
	}
}
