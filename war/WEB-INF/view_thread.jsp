<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="com.ikai.protodemo.proto.ForumThreadProtos"%>
<%@ page import="com.ikai.protodemo.proto.ForumThreadProtos.Post"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Thread</title>
</head>
<body>
  <%
      ForumThreadProtos.Thread thread = (ForumThreadProtos.Thread) request
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

</body>
</html>