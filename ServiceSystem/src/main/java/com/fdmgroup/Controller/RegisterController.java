package com.fdmgroup.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

@Controller
@RequestMapping(value="/register")
public class RegisterController {

	@Resource(name="customerDaoBean")
	private CustomerDao cDao;

	@RequestMapping(method=RequestMethod.GET)
	public String goToRegister(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("registerCustomer", new Customer());
		
		return "register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String goToRegister(Customer customer, HttpSession hs) {
		// TODO Auto-generated method stub
		if("".equals(customer.getEmail()) || "".equals(customer.getUsername()) || "".equals(customer.getPassword())) {
			hs.setAttribute("error_message", "Thre is some information missing. Please try again.");
		}else if(cDao.getByUsernameOrEmail(customer.getUsername(), customer.getEmail()).size() < 1) {
			cDao.createCustomerThenAdd(customer);
			Customer c = cDao.getByUsernameAndPassword(customer.getUsername(), customer.getPassword());
			hs.setAttribute("ActiveUsername", c.getUsername());
			hs.setAttribute("ActiveUserID", c.getId());
			hs.setAttribute("ActiveEmail", c.getEmail());
			hs.setAttribute("VendorStatus", "inactive");
			hs.setAttribute("LoginStatus", "success");
			return "dashboard";
		}else {
			hs.setAttribute("error_message", "Username or Email has been used for registeration. Please try again.");
		}
		
		return "register";
	}

}
