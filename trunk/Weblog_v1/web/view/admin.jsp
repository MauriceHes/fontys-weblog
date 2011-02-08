<%-- 
    Document   : index
    Created on : 7-feb-2011, 13:16:50
    Author     : Jurgen
--%>

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
            <h1>Admin panel</h1>
            <a href="MyBlogController?page=index" id="indexlink">View My Blog</a>
            <div id="adminpane">
                <form action="AddPost" method="POST">
                    <div id="posttitle"><label id="labeltitle" for="inputtitle">Title:</label><input type="text" name="inputtitle" id="inputtitle"/>
                    </div>
                    <div id="postbody"><label id="labelbody" for="inputbody">Body:</label><textarea name="inputbody" id="inputbody" rows="5"></textarea>
                    </div>
                    <button id="buttonpost">Add Post</button>
                </form>
            </div>
            <div id="lijntje"></div>
        </div>
    </body>
</html>
