package com.fdmgroup.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entity.Customer;

@Controller
public class IndexController {
	
	@RequestMapping(value= {"/", "/index"}, method=RequestMethod.GET)
	public String goToIndex(Model model, HttpSession session) {
		if(session.getAttribute("ActiveUserID") == null) {
			session.setAttribute("LoginStatus", "fail");
			model.addAttribute("registerCustomer", new Customer());
			model.addAttribute("loginCustomer", new Customer());
		}
		return "index";
	}
}
