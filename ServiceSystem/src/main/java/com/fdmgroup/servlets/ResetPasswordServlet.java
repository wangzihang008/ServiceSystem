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

public class ResetPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPwd = req.getParameter("old_password");
		String newPwd = req.getParameter("new_password");
		String newPwdRetype = DigestUtils.md5Hex(req.getParameter("retype_new_password"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EMF");
		CustomerDao cd = new CustomerDao(emf);
		HttpSession session = req.getSession();
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/register.jsp");
		if("".equals(oldPwd) && "".equals(newPwd) && "".equals(newPwdRetype)) {
			session.setAttribute("ResetPasswordStatus", "missing");
		}else if(cd.getByUsernameAndPassword((String)session.getAttribute("active_user_name"), oldPwd) != null) {
			session.setAttribute("ResetPasswordStatus", "success");
			Calendar calendar = Calendar.getInstance();
			Long id = Long.parseLong((String) session.getAttribute("active_user_id"));
			Customer customer = cd.get(id);
			customer.setPassword(newPwd);
			customer.setLastUpdatedTime(calendar);
			cd.update(id, customer);
		}else {
			session.setAttribute("ResetPasswordStatus", "fail");
		}
		
		rd.forward(req, resp);
	}
}
