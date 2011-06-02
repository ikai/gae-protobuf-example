// Copyright 2011 Google Inc. All Rights Reserved.

package com.ikai.protodemo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ikai@google.com (Ikai Lan)
 * 
 */
public class SetUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
	    FilterChain chain) throws ServletException, IOException {
	HttpServletRequest request = (HttpServletRequest) req;
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	String currentUrl = request.getRequestURI();
	if (request.getQueryString() != null) {
	    currentUrl = currentUrl + "?" + request.getQueryString();
	}

	if (user != null) {
	    req.setAttribute("currentUser", user);
	    String logoutUrl = userService.createLogoutURL(currentUrl);
	    req.setAttribute("logoutUrl", logoutUrl);
	} else {
	    String loginUrl = userService.createLoginURL(currentUrl);
	    req.setAttribute("loginUrl", loginUrl);
	}

	chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}