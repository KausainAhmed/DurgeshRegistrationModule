package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig 	
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

	
		// getting all the details from incoming user
		String name = req.getParameter("user_name");
		String pass = req.getParameter("user_pass");
		String email = req.getParameter("user_email");
		Part part = req.getPart("iamge");
		String fname = part.getSubmittedFileName();
		out.println(fname);

//connection
		try {
			Thread.sleep(3000);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube","root", "open6162");
			// query
			String q = ("insert into user(name,password,email)values(?,?,?)");
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setString(3, email);
		//	ps.executeUpdate();
			out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error");
		}

		
	}

}
