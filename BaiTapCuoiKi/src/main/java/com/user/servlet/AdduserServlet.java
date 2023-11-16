package com.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.AddUserDao;
import com.entity.User;

/**
 * Servlet implementation class AdduserServlet // Adduser
 */
@WebServlet("/Adduser")
public class AdduserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	
	private AddUserDao adduserDao;

    public void init() {
    	adduserDao = new AddUserDao(null);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phno = request.getParameter("phno");
      
        
       
      
		User u = adduserDao.CheckUsername(name);
		
		if(u != null)
		{
			request.setAttribute("err", "1");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			User adduser = new User();
		        adduser.setName(name);
		        adduser.setEmail(email);
		        adduser.setPassword(password);
		        adduser.setPhno(phno);
		       
		        try {
		        	adduserDao.registerAdduser(adduser);
		            

	                // Ajoutez un attribut "success" pour indiquer que l'inscription a réussi.
	                request.setAttribute("success", "Register Success. Please Login!!!");

	                // Redirigez vers la page de connexion.
	                request.getRequestDispatcher("login.jsp").forward(request, response); 
		                  } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }		}

   
    }
}