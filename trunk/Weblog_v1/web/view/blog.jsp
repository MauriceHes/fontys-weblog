<%-- 
    Document   : index
    Created on : 7-feb-2011, 13:16:50
    Author     : Jurgen
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="model.Posting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="global.css" rel="stylesheet" type="text/css">
        <title>A Web Page</title>
    </head>
    <body>
        <div id="wrapper">
            <a href="blog"><h1>My Blog - about stuff and things and such</h1></a>
            <div id="leftpane">
                <div id="about"><h2>ABOUT</h2><p>text</p></div>
                <div id="pictures"><h2>PICTURES</h2><p>text</p></div>
                <div id="friends"><h2>FRIENDS</h2><p>text</p></div>
            </div>
            <div id="rightpane">
                <%
                    List<Posting> posts = (List)request.getAttribute("posts");
                    DateFormat formatter;
                    formatter = new SimpleDateFormat("MM/dd/yyyy");
                    for(Posting p: posts){
                        out.println("<div class='post'><a href='viewpost?id=" + p.getId() + "'><h2>" + p.getTitle() + "</h2></a><p>" +
                                p.getContent() + "</p><br><p>Posted on: "+ formatter.format(p.getDate()) + "</p></div>");
                    }
                %>
            </div>
        </div>
    </body>
</html>
