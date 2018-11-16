package com.fdmgroup.Controller;

import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.Entity.Vendor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class VendorRegisterControllerTest {
	@Test
	public void When_goToVendorRegister_then_returnVendorRegisterJspPage() {
		VendorRegisterController vgc = new VendorRegisterController();
		Model model = mock(Model.class);
		String nextPage = vgc.goToVendorRegister(model);
		
		assertEquals("vendor/apply", nextPage);
	}
	
	@Test
	public void When_goToVendorRegister_Given_vendorAddressEmpty_then_returnVendorRegisterJspPage() {
		VendorRegisterController vgc = new VendorRegisterController();
		HttpSession hs = mock(HttpSession.class);
		Vendor vendor = new Vendor();
		vendor.setAddress("");
		String nextPage = vgc.goToVendorRegister(vendor, hs);
		
		assertEquals("vendor/apply", nextPage);
	}
	
	@Test
	public void When_goToVendorRegister_Given_vendorIsNoInfoMissing_then_returnVendorRegisterJspPage() {
		VendorRegisterController vgc = new VendorRegisterController();
		HttpSession hs = mock(HttpSession.class);
		Vendor vendor = new Vendor();
		vendor.setAddress("robert");
		vendor.setStoreName("robert");
		vendor.setVendorFirstName("robert");
		vendor.setVendorLastName("robert");
		vendor.setPhoneNumber("robert");
		String nextPage = vgc.goToVendorRegister(vendor, hs);
		
		assertEquals("vendor/dashboard", nextPage);
	}
}
