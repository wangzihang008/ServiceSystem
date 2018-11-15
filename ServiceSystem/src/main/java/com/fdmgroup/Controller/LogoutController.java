package com.fdmgroup.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/logout")
public class LogoutController {
	@RequestMapping(method=RequestMethod.GET)
	public String goToLogout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();		
		return "logout";
	}

}
