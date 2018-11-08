package com.fdmgroup.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		req.setAttribute("luck_numbers", new int [] {1, 4, 6, 2, 7, 8, 3});
		
		// better to call a login service class here
		RequestDispatcher rd = null;
		if(username.equals("Robert") || password.contains("#")) {
			// block login
			rd = req.getRequestDispatcher("block.jsp");
		}else {
			// allow login
			HttpSession session = req.getSession();
			session.setAttribute("active_user", username);
			rd = req.getRequestDispatcher("home.jsp");
		}
		rd.forward(req, resp);
	}
}
