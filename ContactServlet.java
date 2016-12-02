package com.gco;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;


/**
 * Servlet implementation class ContactServlet
 */
 //@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private ServletContext context;
    
   // @Override
    public void init(ServletConfig config) throws ServletException {
     context = config.getServletContext();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	ServletContext sct = getServletContext();
	SignMeUp smu = new SignMeUp();
	smu.connectDB();
	 switch (smu.loginListener(request.getParameter("uname"),
			        request.getParameter("pname"))) { 
			        case 1:
		context.getRequestDispatcher("/login.jsp");
		break;
			        case 2: 
		response.getWriter().write("Wrong user name or password");
		break;
			        case 3:
			        smu.InsertStmt(request.getParameter("uname"),
			        request.getParameter("pname"),
	
			        request.getParameter("fname"), 
					request.getParameter("lname"),
					request.getParameter("stname"),
					request.getParameter("cityname"),
					request.getParameter("statename"),
					request.getParameter("zipno"));
	
		response.getWriter().write("Please register");
		break;
			        case 4:
			response.getWriter().write("Something else went wrong");
			    	}

	response.getWriter().write(request.getParameter("uname"));
	}
	
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
