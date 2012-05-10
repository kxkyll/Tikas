<%-- 
    Document   : tyotunnit
    Created on : 19.4.2012, 17:39:52
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
                        Login
                    </td>
                    <td height="25" width="150" bgcolor="#BDBDBD" align="center">
                        <input type="submit" name="siirryTehtavat" value="Lisää työtehtävä"/>
                    </td>
                    <td height="20" width="150" bgcolor="#FAFAFA" align="center" >
                        Lisää työtunteja

                    </td>
                </tr>
            </table>
            <table bgcolor="#FAFAFA" width=100% border="1" align="left"> 
                <tr> 

                    <td height="10" width=100% colspan="3">
                        Valitse asiakas
                    </td>

                </tr> 
                <%-- <tr onmouseclick= "javascript: submitform()">--%>
                <td width="100%" colspan="3">
                    <select name="asiakas"  width=100% style="width:700px" size="5" autofocus="true"> 

                        <c:forEach items="${asiakkaat}" var="asiakas">
                            <option value=${asiakas.asiakasnumero}> ${asiakas.nimi}</option>
                        </c:forEach>

                    </select> 
                </td>
                <font color="red"> ${virhe}</font>
                </tr> 
                <tr>
                    <td colspan ="3">

                        <input type="submit" name="haeAsiakkaanTehtavat" value="Hae asiakkaan työtehtävät"/>
                        <input type="hidden" name="asiakasnumero"value="${anro}"/>   
                        <input type="hidden" name="nimi"value="${animi}"/>   
                    </td>

                </tr>
                <table bgcolor="#FAFAFA" width=100% border="1" align="left"> 
                    <tr> 

                        <td height="10" width=100% colspan="3">
                            Valitse asiakkaan ${animi} työtehtävä
                        </td>

                    </tr> 

                    <td width="100%" colspan="3">
                        <select name="tehtava"  width=100% style="width:700px" size="5" autofocus="true"> 

                            <c:forEach items="${tyotehtavat}" var="tyotehtava">
                                <option value=${tyotehtava.tyonumero}> ${tyotehtava.kadunnimi} ${tyotehtava.talonnumero} ${tyotehtava.kuvaus}</option>
                            </c:forEach>

                        </select> 
                    </td>
                    <font color="red"> ${virhe}</font>
                    </tr> 
                    <tr>
                        <td colspan ="3">

                            <input type="submit" name="haeTehtavanTiedot" value="Hae työtehtävän tiedot"/>
                        </td>

                    </tr>
                    <tr>        
                        <td width=100% colspan="3"> 
                            <table id="tehtavat">
                                <th>Nro</th>
                                <th>Tila</th>
                                <th>Laji</th>
                                <th>Kuvaus</th>
                                <th>Katusoite</th>
                                <th>Postiosoite</th>
                                <th>Yhteyshenkilo</th>
                                <th>Puhelinnumero</th>
                                <th>Vastuuhenkilö</th>
                                <th>Toivepvm</th>

                    </tr>


                    <tr>
                        <td>${tehtavanTiedot.tyonumero}</td>
                        <td>${tehtavanTiedot.tila}</td>
                        <td>${tehtavanTiedot.tyolaji}</td>
                        <td>${tehtavanTiedot.kuvaus}</td>
                        <td>${tehtavanTiedot.kadunnimi} ${tehtavanTiedot.talonnumero}</td>
                        <td>${tehtavanTiedot.postinumero} ${tehtavanTiedot.postitoimipaikka}</td>
                        <td>${tehtavanTiedot.asiakkaanyhteyshenkilo}</td>
                        <td>${tehtavanTiedot.puhelinnumero}</td>
                        <td>${tehtavanTiedot.vastuuhenkilo}</td>
                        <td>${tehtavanTiedot.toivepvm}</td>
                    </tr>

                </table> </td>
                <tr>        
                    <td width=100%> <input type="text" name="tehtavanumero" value=${tnro}>
                        <table id="tunnit">
                            <th>Tuntia (hh.mm) </th>
                            <th>Tekijä</th>

                </tr>


                <tr>
                    <c:forEach items="${tyot}" var="tyo">
                        <td>${tyo.tunnit}</td>
                        <td>${tyo.henkilo}</td>
                    </c:forEach>
                    <td><input type="time" name="tunnit"></td>
                        <td>${tekija}</td>
                </tr>
                <tr> <td colspan="2">
                <input type="submit" name="lisaaTunnit" value="Lisää työtunnit"/>
                <input type="hidden" name="tekija" value ="${tekija}"/>
                    </td>
                </tr>
            </table> </td>

    </table>
