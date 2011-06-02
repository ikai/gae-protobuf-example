<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Thread</title>
</head>
<body>
  <h1>
    Viewing All Threads
  </h1>

  <form action="/add_thread" method="POST">
    <p>
      Title: <input type="text" name="title" />
    </p>
    <p>
      Body:
      <textarea name="body">Your body here ... </textarea>
    </p>
    <input type="submit">
  </form>

  <%
      for (Entity thread : (List<Entity>) request.getAttribute("threads")) {
  %>
  <h1><a href="/view_thread?key=<%= KeyFactory.keyToString(thread.getKey()) %>"><%=thread.getProperty("title")%></a></h1>
  <%
      }
  %>

</body>
</html>