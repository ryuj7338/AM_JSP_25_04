package com.KoreaIT.java.AM_jsp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/printDan")
public class PrintDanServlet extends HttpServlet {
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		
		
		
//		response.getWriter().append("8 * 1 = 8\n");
//		response.getWriter().append(String.format("%d * %d = %d<br>", 8, 1, 8));
//		<br> : 줄바꿈
		
		
	
		
		int dan = Integer.parseInt(request.getParameter("dan"));
		
		response.getWriter().append(String.format("== %d단 ==<br>", dan));
		
		for (int i = 1; i <= 9; i++ ) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, dan * i));
		}
		
		
	}


}