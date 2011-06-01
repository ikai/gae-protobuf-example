package com.ikai.protodemo;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.ikai.protodemo.proto.ForumThreadProtos;
import com.ikai.protodemo.proto.ForumThreadProtos.Post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ViewThreadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException, ServletException {
	String keyString = req.getParameter("key");
	Key key = KeyFactory.stringToKey(keyString);

	DatastoreService datastore = DatastoreServiceFactory
		.getDatastoreService();
	try {
	    Entity entity = datastore.get(key);
	    Blob data = (Blob) entity.getProperty("data");

	    ForumThreadProtos.Thread thread = ForumThreadProtos.Thread
		    .parseFrom(data.getBytes());

	    req.setAttribute("thread", thread);
	    req.getRequestDispatcher("WEB-INF/view_thread.jsp").forward(req,
		    resp);

	} catch (EntityNotFoundException e) {
	    resp.sendError(404);
	}
    }
}
