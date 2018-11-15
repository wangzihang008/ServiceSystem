package com.fdmgroup.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.fdmgroup.Entity.Customer;
import com.fdmgroup.Entity.Dao.CustomerDao;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = DigestUtils.md5Hex(req.getParameter("password"));
		
		// better to call a login service class here
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");
		CustomerDao cd = new CustomerDao(emf);
		Customer customer = cd.getByUsernameAndPassword(username, password);
		HttpSession session = req.getSession();
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		if(session.getAttribute("active_user_id") != null) {
			
		}else if(customer != null) {
			session.setAttribute("LoginStatus", "success");
			session.setAttribute("active_user_id", customer.getId());
			session.setAttribute("active_user_name", customer.getUsername());
			session.setAttribute("active_user_email", customer.getEmail());
			Calendar calendar = Calendar.getInstance();
			customer.setLastLogDateTime(calendar);
			cd.update(customer.getId(), customer);
		}else {
			session.setAttribute("LoginStatus", "fail");
		}
		
		rd.forward(req, resp);
	}
}
