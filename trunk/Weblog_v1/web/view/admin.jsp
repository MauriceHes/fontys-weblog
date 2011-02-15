<%-- 
    Document   : index
    Created on : 7-feb-2011, 13:16:50
    Author     : Jurgen
--%>

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
            <a href="admin"><h1>Admin panel</h1></a>
            <a href="blog" id="indexlink">View My Blog</a>
            <div id="adminpane">
                <%
                List<Posting> posts = (List)request.getAttribute("posts");
                if(request.getParameter("edit") != null)
                {
                    for(Posting p: posts){
                        if(p.getId() == Long.parseLong(request.getParameter("edit")))
                        {
                        %>
                            <form action="addpost?action=edit" method="POST">
                                <div id="posttitle"><label id="labeltitle" for="inputtitle">Title:</label><input type="text" value="<%=p.getTitle()%>" name="inputtitle" id="inputtitle"/>
                                </div>
                                <div id="postbody"><label id="labelbody" for="inputbody">Body:</label><textarea name="inputbody" id="inputbody" rows="15"><%=p.getContent()%></textarea>
                                </div>
                                <input type="hidden" value="<%=p.getId()%>" name="postid" />
                                <button id="buttonpost">Save Post</button>
                            </form>
                        <%
                        }
                    }
                }else{
                %>
                <form action="addpost?action=new" method="POST">
                    <div id="posttitle"><label id="labeltitle" for="inputtitle">Title:</label><input type="text" name="inputtitle" id="inputtitle"/>
                    </div>
                    <div id="postbody"><label id="labelbody" for="inputbody">Body:</label><textarea name="inputbody" id="inputbody" rows="5"></textarea>
                    </div>
                    <button id="buttonpost">Add Post</button>
                </form>
                <%
                }
                %>
            </div>
            <div id="lijntje"></div>
            
            <%
            if(session.getAttribute("mode") == "advanced"){
                    out.println("<a href='admin?mode=basic'>Basic mode</a>");
            %>
            <div id="editpane">
                <%
                    for(Posting p: posts){
                        out.println("<div class='post'><span class='edittitle'>" + p.getTitle() + "</span><span class='editdate'>" +
                                p.getDate() + "</span><span class='editpost'><a href='admin?edit="+p.getId()+"'>EDIT</a></span><span class='deletepost'><a href='admin?delete="+p.getId()+"'>DELETE</a></span></div>");
                    }
                %>
            </div>
            <%
            }else{
                out.println("<a href='admin?mode=advanced'>Advanced mode</a>");
            }
            %>
        </div>
    </body>
</html>
