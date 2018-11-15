package com.fdmgroup.Controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DashboardControllerTest {
	@Test
	public void When_goToDashboard_Then_returnDahboardJspPage() {
		DashboardController dc = new DashboardController();
		String nextPage = dc.goToDashboard();
		
		assertEquals("dashboard", nextPage);
	}
	
	@Test
	public void When_goToVendorApply_Then_returnVendorApplyJspPage() {
		DashboardController dc = new DashboardController();
		String nextPage = dc.goToVendorApply();
		
		assertEquals("vendor/apply", nextPage);
	}
}
