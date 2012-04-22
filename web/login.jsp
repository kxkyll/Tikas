<%-- 
    Document   : login
    Created on : 19.4.2012, 17:07:53
    Author     : Kati
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tyotehtavat - Login </title>
    </head>
    <body bgcolor="#F8ECE0" background="images/lightgray1.gif"> 

        <form method="post">
            <table width=100% border="2" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td> <font face="MATISSE ITC" size="8" color="black">Työtehtävien kirjausjärjestelmä</font>
                    </td>
                    <td height="20" align="right"> 

                        Käyttäjätunnus: <input type='text' name='ktunnus' maxlength="8" /> 
                        Salasana: <input type='password' name='salasana' maxlength="16" /><input type='submit' value='Kirjaudu' />
                        <br>
                        <a href="Logout" target="">Kirjaudu ulos</a>
                    </td>
                </tr>

            </table>
            <table border="0" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td height="20" width="150" bgcolor="#FAFAFA" align="center" >
                        Login
                    </td>
                    <td height="25" width="150" bgcolor="#BDBDBD" align="center">
                        Lisää työtehtävä
                    </td>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center">
                        Lisää työtunteja
                    </td>
                </tr>
            </table>
