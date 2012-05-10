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
                    <td height="25" width="150" bgcolor="#FAFAFA" align="center">
                        Lisää työtehtävä
                    </td>
                    <td height="20" width="150" bgcolor="#BDBDBD" align="center" >
                        <input type="submit" name="siirryTunnit" value="Lisää työtunteja"/>
                        
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

                        <input type="submit" name="haeAsiakas" value="Hae asiakastiedot"/>
                        <input type="hidden" name="asiakasnumero"value="${anro}"/>   
                        <input type="hidden" name="nimi"value="${animi}"/>   
                    </td>

                </tr>

                <th >Asiakkaan nimi</th>   <th>Asiakasnumero</th> <th>Yhteyshenkilö</th>

                <tr>
                    <td width= 100% > <text name="asiakasnimi"> ${animi} </td>
                    <td> <text> ${anro}</text> </td>

                    <td> <input class="syote" type="text" name="yhteyshenkilo"  value="${ayhteyshlo}" /> </td>  

                </tr>

                <tr> 
                    <th>Katuosoite</th>   <th>Postiosoite</th>    <th>Puhelin</th> 
                </tr> 
                <tr> 
                    <td><input class="syote" type="text" name="katuosoite" value="${akatu}"/></td>  
                    <td><input class="syote" type="text" name="postiosoite"value="${aposti}"/> 

                    <td><input class="syote" type="tel" name="puhelin"value="${apuhelin}"/></td> 
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
                            <option selected ="selected" value="KON"> Konsultointi </option> 
                            <option value="SUU"> Suunnittelu </option> 
                            <option value="TOT"> Toteutus </option> 
                            <option value="YLL"> Ylläpito </option> 
                        </select> 
                    </td>   
                    <td><select name="tyotilavalinta"> 
                            <option>Valitse työn kiireellisyys </option> 
                            <option selected ="selected" value="N"> Normaali </option> 
                            <option value="K"> Kiire </option> 
                        </select> 
                    <td> <input class="syote" type="date" name="toivepaiva" value="${paiva}"/>  
                    </td> 
                <tr onmousedown='alert("onmousedown effect test....")'> 
                    <td  colspan="3">
                        Työtehtävän kuvaus
                    </td>
                </tr>

                <tr>
                    <td width=100%>

                        <textarea class="syote" rows="2" cols="115" name ="kuvaus">${tehtavakuvaus} </textarea>

                    </td>
                </tr>
                <tr></tr> 
                <tr>
                    <td>

                        <input type="submit" name="lisaaTyo" value="Lisää tyotehtava"/>
                        <BUTTON name="reset" value ="peruuta">Peruuta</BUTTON>
                    </td>
                <font color="red"> ${virhe}</font>
                </tr>
                <tr> <td width=100% colspan="3"> Asiakkaan kaikki työtehtävät </td> </tr>
                <tr onmouseup= 'alert ("hiiri alas")'>


                <tr>        
                    <td width=100% colspan="3"> <table id="tehtavat">
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

                <c:forEach items="${tyotehtavat}" var="tyotehtava">
                    <tr>
                        <td>${tyotehtava.tyonumero}</td>
                        <td>${tyotehtava.tila}</td>
                        <td>${tyotehtava.tyolaji}</td>
                        <td>${tyotehtava.kuvaus}</td>
                        <td>${tyotehtava.kadunnimi} ${tyotehtava.talonnumero}</td>
                        <td>${tyotehtava.postinumero} ${tyotehtava.postitoimipaikka}</td>
                        <td>${tyotehtava.asiakkaanyhteyshenkilo}</td>
                        <td>${tyotehtava.puhelinnumero}</td>
                        <td>${tyotehtava.vastuuhenkilo}</td>
                        <td>${tyotehtava.toivepvm}</td>
                    </tr>
                </c:forEach>
            </table> </td>

    </table>


</form>

<br>


</body>
</html>
<script type="text/javascript">
    function submitform()
    {
        document.forms.submit();
    }
</script>
