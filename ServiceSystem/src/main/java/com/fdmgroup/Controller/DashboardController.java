package com.fdmgroup.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {

	@RequestMapping(method=RequestMethod.GET)
	public String goToDashboard() {
		// TODO Auto-generated method stub
		return "dashboard";
	}

	@RequestMapping(value="/dashboard/vendor/apply", method=RequestMethod.GET)
	public String goToVendorApply() {
		// TODO Auto-generated method stub
		return "vendor/apply";
	}

}
