<%-- 
    Document   : tyotehtavat
    Created on : 28.3.2012, 10:11:51
    Author     : Kati
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tyotehtavat</title>
    </head>
    <body>
        <form method="post">
            <input type="text" name="kuvaus"/>
            <input type="submit" value="Uusi tyotehtava"/>
        </form>
        
        <c:forEach items="${tyotehtavat}" var="tyotehtava">
            ${tyotehtava.tyonumero} <br/>
            ${tyotehtava.kuvaus} <br/>
        </c:forEach>
    </body>
</html>

