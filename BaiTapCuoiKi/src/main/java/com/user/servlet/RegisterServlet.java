package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
    
    
	
	private UserDAOImpl adduserDao;

    public void init() {
    	adduserDao = new UserDAOImpl(null);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String name=req.getParameter("fname");
			String email=req.getParameter("email");
			String phno=req.getParameter("phno");
			String password=req.getParameter("password");
			String check=req.getParameter("check");
			
			
				User adduser = new User();
			        adduser.setName(name);
			        adduser.setEmail(email);
			        adduser.setPassword(password);
			        adduser.setPhno(phno);
			        adduser.setAddress(check);
			        try {
			        	adduserDao.userRegister(adduser);
			            
			                  } catch (Exception e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			        }		

	        resp.sendRedirect("login.jsp");
	    }
	}