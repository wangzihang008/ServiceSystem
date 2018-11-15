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
@RequestMapping(value = "/login")
public class LoginController {

	@Resource(name = "customerDaoBean")
	private CustomerDao cDao;

	@RequestMapping(method=RequestMethod.GET)
	public String goToLogin(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("loginCustomer", new Customer());
		
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String goToLogin(Model model, Customer customer, HttpSession session) {
		// TODO Auto-generated method stub
		model.addAttribute("loginCustomer", new Customer());
		if("".equals(customer.getUsername()) || "".equals(customer.getPassword())) {
			session.setAttribute("ErrorMessage", "There is some information missing. Please try again.");
		}else {
			Customer c = cDao.getByUsernameAndPassword(customer.getUsername(), customer.getPassword());
			if(c == null) {
				session.setAttribute("ErrorMessage", "Username or password is invaild. Please try again.");
			}else{
				session.setAttribute("LoginStatus", "success");
				session.setAttribute("ActiveUsername", c.getUsername());
				session.setAttribute("ActiveUserID", c.getId());
				session.setAttribute("ActiveEmail", c.getEmail());
				if(c.getVendor() != null) {
					session.setAttribute("VendorStatus", "active");
				}else {
					session.setAttribute("VendorStatus", "inactive");
				}
				return "dashboard";
			}
		}
		return "login";
	}

}
