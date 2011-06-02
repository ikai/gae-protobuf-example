package com.ikai.protodemo;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.ikai.protodemo.proto.ForumThreadProtos.ForumThread;
import com.ikai.protodemo.proto.ForumThreadProtos.Post;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AddThreadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	String title = req.getParameter("title");
	String body = req.getParameter("body");
	long timestamp = System.currentTimeMillis();
	Random gen = new Random();
	int id = gen.nextInt();

	Post post = Post.newBuilder().setId(id).setTitle(title).setBody(body)
		.setTimestamp(timestamp).build();

	ForumThread thread = ForumThread.newBuilder()
						.addPost(post)
						.build();

	DatastoreService datastore = DatastoreServiceFactory
		.getDatastoreService();
	Entity entity = new Entity("Thread");
	entity.setProperty("data", new Blob(thread.toByteArray()));
	entity.setProperty("created", new Date());	
	entity.setProperty("lastUpdated", new Date());
	entity.setUnindexedProperty("title", title);
	
	Key key = datastore.put(entity);

	resp.sendRedirect("/view_thread?key=" + KeyFactory.keyToString(key));

    }
}
