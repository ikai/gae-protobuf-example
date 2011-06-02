package com.ikai.protodemo;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ViewAllThreadsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException, ServletException {

	DatastoreService datastore = DatastoreServiceFactory
		.getDatastoreService();
	Query query = new Query("Thread");
	query.addSort("lastUpdated", SortDirection.DESCENDING);
	List<Entity> results = datastore.prepare(query).asList(
		FetchOptions.Builder.withDefaults());

	req.setAttribute("threads", results);

	req.getRequestDispatcher("WEB-INF/view_all_threads.jsp").forward(req,
		resp);
    }
}
