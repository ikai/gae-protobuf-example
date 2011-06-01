package com.ikai.protodemo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ViewThreadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException, ServletException {
	req.getRequestDispatcher("WEB-INF/view_thread.jsp").forward(req, resp);
    }
}
