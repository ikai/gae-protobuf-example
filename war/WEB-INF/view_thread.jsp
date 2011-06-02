<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.ikai.protodemo.proto.ForumThreadProtos.ForumThread"%>
<%@ page import="com.ikai.protodemo.proto.ForumThreadProtos.Post"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Thread</title>
</head>
<body>
  <%
      ForumThread thread = (ForumThread) request
  		    .getAttribute("thread");
  %>

  <%
      for (Post post : thread.getPostList()) {
  %>
  <h1><%=post.getTitle()%></h1>
  <p><%=post.getBody()%></p>
  <%
      }
  %>

  <form action="/reply" method="POST">
    <p>
      Title: <input type="text" name="title" />
    </p>
    <p>
      Body:
      <textarea name="body">Your body here ... </textarea>
    </p>
    <input type="hidden" name="key" value="<%= request.getParameter("key") %>" />
    <input type="submit">
  </form>


</body>
</html>