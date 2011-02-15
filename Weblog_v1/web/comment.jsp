<%-- 
    Document   : comment
    Created on : Feb 15, 2011, 1:07:17 PM
    Author     : DeluxZ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String commentbody = request.getParameter("commentbody");

    out.println("<div class='comment'>"+commentbody+"</div>");
%>
