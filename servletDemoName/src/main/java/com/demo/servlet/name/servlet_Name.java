package com.demo.servlet.name;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet_Name
 */
@WebServlet("/testdemo")
public class servlet_Name extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet_Name() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		PrintWriter p=response.getWriter();
//		p.write("Hi,Warm Welcomes you!");
//		String name=request.getParameter("typeName");
//		p.println("This website welcomes you Mr/Miss "+name);
//		
//
//		HttpSession session=request.getSession();
//		session.setAttribute("name", name);
//		
//		Cookie c=new Cookie("name", name+"");
//		response.addCookie(c);
//		
//		response.sendRedirect("servlet_Process2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter p=response.getWriter();
		p.write("Hi,Warm Welcomes you!");
		String name=request.getParameter("typeName");
		String desc=request.getParameter("desc");
		p.println("This website welcomes you Mr/Miss "+name);
		
//		HttpSession session=request.getSession();
//		session.setAttribute("name", name);
		
//		Cookie c=new Cookie("name", name+"");
//		response.addCookie(c);
//		
//		response.sendRedirect("servlet_Process2");

//		RequestDispatcher rd =request.getRequestDispatcher("servlet_Process2");
//		request.setAttribute("name", name);
//		rd.forward(request, response);
		
		RequestDispatcher rd =request.getRequestDispatcher("servlet_Process2");
		request.setAttribute("name", name);
		rd.include(request, response);
		
		
		p.println(desc);
		p.close();
		
	}

}
