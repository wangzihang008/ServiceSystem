package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class LogoutControllerTest {
	@Test
	public void When_goToLogout_Then_returnLogoutJspPage() {
		LogoutController lc = new LogoutController();
		HttpSession session = mock(HttpSession.class);
		String nextPage = lc.goToLogout(session);
		
		verify(session).invalidate();
		assertEquals("logout", nextPage);
	}
}
