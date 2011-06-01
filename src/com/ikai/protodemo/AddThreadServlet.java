package com.ikai.protodemo;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AddThreadServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	resp.setContentType("text/plain");
	resp.getWriter().println("Hello, world");
    }
}
