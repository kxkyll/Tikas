<%-- 
    Document   : logout
    Created on : 21.4.2012, 18:31:56
    Author     : Kati
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <STYLE type="text/css">
            input.syote {color:blue}
            textarea.syote {color:blue}
            ol { text-align: left;}      

            li {
                display: table-cell;
                width: 20em; 
                padding-right: 5px;
                padding-left: 5px;
                border-right: solid 1px;}
            #tehtavat
            {
                font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
                width:100%;
                border-collapse:collapse;
            }
            #tehtavat td, #tehtavat th 
            {
                font-size:14px;
                border:1px solid #1F1F14;
                padding:3px 7px 2px 7px;
            }
            #tehtavat th 
            {
                font-size:14px;
                text-align:left;
                padding-top:5px;
                padding-bottom:4px;
                
                background-color:#BDBDBD;
                color:#1F1F14;
            }
            #tehtavat tr.alt td 
            {
                color:#000000;
                background-color:#EAF2D3;
            }


        </STYLE>

        <title>Tyotehtavat</title>
    </head>
    <body bgcolor="#F8ECE0" background="images/lightgray1.gif" bgproperties ="fixed"> 

        <form id ="tehtavat" action="" method="post">
            <table id="tiedot" width=100% border="2" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td> <font face="MATISSE ITC" size="8" color="black">Työtehtävien kirjausjärjestelmä</font>
                    </td>
                    <td height="20" align="right"> 

                        <font color="blue"> Olet kirjautunut käyttäjätunnuksella: ${ktunnus} 
                        <a href="Logout" target="">Kirjaudu ulos</a></font>

                    </td>
                </tr>

            </table>
            <table border="0" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center" >
                       <input type="submit" name="siirryLogin" value="Login"/>
                    </td>
                    <td height="25" width="150" bgcolor="#BDBDBD" align="center">
                        Lisää työtehtävä
                    </td>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center" >
                        Lisää työtunteja
                                              
                    </td>
                </tr>
            </table>
      <font color="blue"> ${viesti}</font>