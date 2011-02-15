<%-- 
    Document   : viewblog
    Created on : 14-feb-2011, 13:36:49
    Author     : Jurgen
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.Comment"%>
<%@page import="model.Posting"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="global.css" rel="stylesheet" type="text/css">
        <script src="http://code.jquery.com/jquery-1.5.min.js" type="text/javascript"></script>
        <script src="scripts.js" type="text/javascript"></script>
        <title>A Web Page</title>
    </head>
    <body>
        <div id="wrapper">
            <a href="blog"><h1>My Blog - about stuff and things and such</h1></a>
            <div id="leftpane">
                <div id="about"><h2>ABOUT</h2><p>A basic blog</p></div>
                <div id="pictures"><h2>PICTURES</h2><p>No pictures yet</p></div>
                <div id="friends"><h2>FRIENDS</h2><p>No friends yet</p></div>
            </div>
            <div id="rightpane">
                <%
                    Posting p = (Posting)request.getAttribute("post");
                    DateFormat formatter;
                    formatter = new SimpleDateFormat("MM/dd/yyyy");
                    List<Comment> clist = p.getComments();
                    String cstring = "";
                    for(Comment c: clist) {
                        cstring += ("<div class='comment'>" + c.getContent() + "</div>");
                    }

                        out.println("<div class='post'><h2>" + p.getTitle() + "</h2><p>" +
                                p.getContent() + "</p><br><p>Posted on: "+ formatter.format(p.getDate()) + "</p>" + cstring + "</div>");

                %>

                    <div id="addcomment">
                        <form id="commentform" action="#" method="POST">
                                <label id="labelcommentbody" for="commentbody">New comment:</label>
                                <textarea name="commentbody" id="commentbody" rows="5"></textarea>
                                <%
                                    out.println("<input type='hidden' name='postid' id='postid' value='"+p.getId()+"' />");
                                %>
                            <button id="buttonpost">Add Comment</button>
                        </form>
                    </div>
                
            </div>
        </div>
    </body>
</html>
