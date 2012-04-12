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
    <body>
        <form method="post">
            <table width="600" border="3" align="left"> 
                <tr> 

                    <td height="10" colspan="3">
                        Valitse asiakas
                    </td>

                </tr> 
                <tr>
                    <td colspan ="3">
                        <select name="asiakas" size="5" autofocus="true"> 

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
                
                    <th>Asiakkaan nimi</th>   <th>Asiakasnumero</th> <th>Yhteyshenkilö</th>
                 
                <tr>
                    <td> <input type="text" name="asiakasnimi" value="${animi}"/> </td>
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
                <tr>
                    <td>

                        <input type="submit" name="lisaaTyo" value="Lisää tyotehtava"/>
                    </td>
                </tr>
                <tr> <td> Asiakkaan työtehtävät </td> </tr>
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

