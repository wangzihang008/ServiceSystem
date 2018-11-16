package com.fdmgroup.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entity.Vendor;

@Controller
public class VendorRegisterController {

	@RequestMapping(value="vendor/apply", method=RequestMethod.GET)
	public String goToVendorRegister(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("applyVendor", new Vendor());
		return "vendor/apply";
	}
	
	@RequestMapping(value="vendor/apply", method=RequestMethod.POST)
	public String goToVendorRegister(Vendor vendor, HttpSession session) {
		if(vendor.isInfoMissing()) {
			session.setAttribute("VendorApplyErrorMessage", "Information missing. Please try again.");
			return "vendor/apply";
		}else {
			
			return "vendor/dashboard";
		}
	}
}
