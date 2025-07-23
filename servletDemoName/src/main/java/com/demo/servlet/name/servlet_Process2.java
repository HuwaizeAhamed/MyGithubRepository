package com.demo.servlet.name;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class servlet_Process2
 */
@WebServlet("/servlet_Process2")
public class servlet_Process2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_Process2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name="";
		
//		HttpSession session=request.getSession();		
//		name=(String)session.getAttribute("name");
	
		PrintWriter pw=response.getWriter();
		
		pw.println("Entering into doget Method");

		
		Cookie cookies[]=request.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equalsIgnoreCase("name")) {
				 name=(String) c.getValue();
				
			}
		}
		
		pw.println("Congragulations !!");
		pw.println("You have redirected to Second servlet " +name);
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String name=(String) request.getAttribute("name");
		
//		HttpSession session=request.getSession();
//		String name=(String)session.getAttribute("name");
		
		
		
		
		
//		Cookie cookies[]=request.getCookies();
//		for(Cookie c:cookies) {
//			if(c.getName().equalsIgnoreCase("name")) {
//				 name=(String) c.getValue();
//				
//			}
//		}
		PrintWriter pw=response.getWriter();
		pw.println("Entering into dopost Method");
		
		pw.println("Congragulations !!");
		pw.println("You have redirected to Second servlet "+name);
		
	}

}
