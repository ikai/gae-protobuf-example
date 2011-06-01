package com.ikai.protodemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ViewAllThreadsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException, ServletException {
	
	req.getRequestDispatcher("WEB-INF/view_all_threads.jsp").forward(req, resp);
    }
}
