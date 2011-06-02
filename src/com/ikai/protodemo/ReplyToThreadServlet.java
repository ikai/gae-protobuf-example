package com.ikai.protodemo;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import com.ikai.protodemo.proto.ForumThreadProtos.ForumThread;
import com.ikai.protodemo.proto.ForumThreadProtos.Post;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ReplyToThreadServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws IOException {
	String keyString = req.getParameter("key");
	String parentIdString = req.getParameter("parentId");
	String title = req.getParameter("title");
	String body = req.getParameter("body");

	long timestamp = System.currentTimeMillis();
	Random gen = new Random();
	int id = gen.nextInt();

	// We are replying to the main thread, no parent comment
	if (keyString != null && parentIdString == null) {
	    Key key = KeyFactory.stringToKey(keyString);

	    DatastoreService datastore = DatastoreServiceFactory
		    .getDatastoreService();
	    try {
		Entity entity = datastore.get(key);
		Blob data = (Blob) entity.getProperty("data");

		ForumThread thread = ForumThread
			.parseFrom(data.getBytes());

		Post post = Post.newBuilder().setId(id).setTimestamp(timestamp)
			.setTitle(title).setBody(body).build();
		List<Post> posts = thread.getPostList();
		
		thread = ForumThread.newBuilder()
			.addAllPost(posts).addPost(post).build();
		
		entity.setProperty("data", new Blob(thread.toByteArray()));
		entity.setProperty("lastUpdated", new Date());
		datastore.put(entity);

	    } catch (EntityNotFoundException e) {
		resp.sendError(401, "Need to supply a thread key");
	    }

	    resp.sendRedirect("view_thread?key=" + keyString);
	} else if (keyString != null && parentIdString != null) {
	    // Replying to a comment

	    resp.sendRedirect("view_thread?key=" + keyString);
	} else {
	    resp.sendError(401, "Need to supply a thread key");
	}
    }
}
