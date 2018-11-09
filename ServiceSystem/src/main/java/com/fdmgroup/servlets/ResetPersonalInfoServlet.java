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

public class ResetPersonalInfoServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = DigestUtils.md5Hex(req.getParameter("password"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");
		CustomerDao cd = new CustomerDao(emf);
		HttpSession session = req.getSession();
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
		if("".equals(username) && "".equals(email) && "".equals(password)) {
			session.setAttribute("RegisterStatus", "missing");
		}else if(cd.getByUsernameOrEmail(username, email).size() < 1) {
			session.setAttribute("RegisterStatus", "success");
			Calendar calendar = Calendar.getInstance();
			Customer customer = new Customer(username, password, email, "active", calendar, calendar, calendar);
			cd.add(customer);
		}else {
			session.setAttribute("RegisterStatus", "fail");
		}
		
		rd.forward(req, resp);
	}
}
