<%-- 
    Document   : tyotehtavat
    Created on : 28.3.2012, 10:11:51
    Author     : Kati
--%>
<%-- bordercolor="blue"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tyotehtavat</title>
    </head>
    <body bgcolor="#F8ECE0" background="images/lightgray1.gif"> 

        <form method="post">
            <table width=100% border="2" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td> <font face="MATISSE ITC" size="8" color="black">Työtehtävien kirjausjärjestelmä</font>
                    </td>
                    <td height="20" align="right"> 

                        Käyttäjätunnus: <input type='text' name='ktunnus' /> Salasana: <input type='password' name='salasana' /><input type='submit' value='Kirjaudu' />
                                <a href="Logout" target="">Kirjaudu ulos</a>
                    </td>
                </tr>

            </table>
            <table border="0" bgcolor="#BDBDBD" align="left"> 
                <tr>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center" >
                        Login
                    </td>
                    <td height="25" width="150" bgcolor="#FAFAFA" align="center">
                        Lisää työtehtävä
                    </td>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center">
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
                <tr>
                    <td colspan ="3">
                        <select name="asiakas" width=100% size="5" autofocus="true"> 

                            <c:forEach items="${asiakkaat}" var="asiakas">
                                <option value=${asiakas.asiakasnumero}> ${asiakas.nimi}</option>
                            </c:forEach>

                        </select> 

                    </td>
                </tr> 
                <tr>
                    <td colspan ="3">

                        <input type="submit" name="haeAsiakas" value="Hae asiakastiedot"/>
                    </td>
                </tr>

                <th >Asiakkaan nimi</th>   <th>Asiakasnumero</th> <th>Yhteyshenkilö</th>

                <tr>
                    <td width= 100% > <input type="text" name="asiakasnimi" value="${animi}"/> </td>
                    <td> <input type="text" name="asiakasnumero" value="${anro}"/> </td>
                    <td><input type="text" name="yhteyshenkilo"value="${ayhteyshlo}"/></td>   
                </tr>

                <tr> 
                    <th>Katuosoite</th>   <th>Postiosoite</th>    <th>Puhelin</th> 
                </tr> 
                <tr> 
                    <td><input type="text" name="katuosoite" value="${akatu}"/></td>  
                    <td><input type="text" name="postiosoite"value="${aposti}"/> 

                    <td><input type="tel" name="puhelin"value="${apuhelin}"/></td> 
                </tr> 
                <tr>
                    <td height="10" colspan="3">
                        Lisättävän työtehtävän tiedot
                    </td>
                </tr>
                <tr> 
                    <th>Työlaji</th>   <th>Kiireellisyys</th>   <th>Suorituspäivä (ddmmyyyy)</th> 
                </tr> 
                <tr> 
                    <td><select name="tyolajivalinta"> 
                            <option>Valitse työlaji </option> 
                            <option value="KON"> Konsultointi </option> 
                            <option value="SUU"> Suunnittelu </option> 
                            <option value="TOT"> Toteutus </option> 
                            <option value="YLL"> Ylläpito </option> 
                        </select> 
                    </td>   
                    <td><select name="tyotilavalinta"> 
                            <option>Valitse työn kiireellisyys </option> 
                            <option value="N"> Normaali </option> 
                            <option value="K"> Kiire </option> 
                        </select> 
                    <td> <input type="date" name="toivepaiva"   
                    </td> 
                <tr>
                    <td  colspan="3">
                        Työtehtävän kuvaus
                    </td>
                </tr>

                <tr>
                    <td width="100" rowspan="2" colspan="3">
                        <textarea rows="10" cols="57" name ="kuvaus"> </textarea>

                    </td>
                </tr>
                <tr></tr> 
                <tr>
                    <td>

                        <input type="submit" name="lisaaTyo" value="Lisää tyotehtava"/>
                    </td>
                </tr>
                <tr> <td> Asiakkaan kaikki työtehtävät </td> </tr>
                <tr>
                    <td width="100" rowspan="6" colspan="4">

                        <c:forEach items="${tyotehtavat}" var="tyotehtava">
                            ${tyotehtava.tyonumero}
                            ${tyotehtava.tila}
                            ${tyotehtava.kuvaus}
                            ${tyotehtava.kadunnimi}
                            ${tyotehtava.talonnumero}
                            ${tyotehtava.postinumero} 
                            ${tyotehtava.postitoimipaikka} 
                            ${tyotehtava.asiakkaanyhteyshenkilo} 
                            ${tyotehtava.puhelinnumero} 
                            ${tyotehtava.vastuuhenkilo} 
                            ${tyotehtava.toivepvm} <br>
                        </c:forEach>

                    </td>
                </tr>
            </table>  


        </form>

        <br>


    </body>
</html>

