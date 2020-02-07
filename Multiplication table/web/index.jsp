<%-- 
    Document   : index
    Created on : Jan 29, 2020, 2:16:31 PM
    Author     : rimid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daugyba</title>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;

            }

            th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            h1 {
                text-align: center;
                padding: 20px;
                color: red;
            }
            h3 {
                text-align: center;
                padding: 20px;
                color: green;
            }
            form {
                width: 100%;
                text-align: center;
                font-size: 13px;
                text-transform: uppercase;  
                padding: 20px;
                color: blue;
            }

        </style>
    </head>
    <body>
        <h1>Kintamo dydžio daugybos lentelė!</h1>

        <%
            int nuo, iki;
            try {
                nuo = Math.abs(Integer.parseInt(request.getParameter("nuo")));
            } catch (Exception ex) {
                nuo = 1;
            }
            try {
                iki = Math.abs(Integer.parseInt(request.getParameter("iki")));
            } catch (Exception ex) {
                iki = 10;
            }
            if (nuo > iki) {
                iki = Math.abs(Integer.parseInt(request.getParameter("nuo")));
                nuo = Math.abs(Integer.parseInt(request.getParameter("iki")));
            }
        %>


        <table>
            <tr>
                <th style="border: none;"></th>
                    <%
                        for (int i = nuo; i <= iki; i++) {
                    %>
                <th style=background-color:blanchedalmond><%=i%></th>
                    <%
                        }
                    %>
            </tr>
            <%
                for (int k = nuo; k <= iki; k++) {
            %>
            <tr> <th style=background-color:blanchedalmond><%=k%></th> 
                    <%
                        for (int j = nuo; j <= iki; j++) {
                            int a = k * j;
                    %>
                <th ><%=a%></th>
                    <%
                        }
                    %>
            </tr>
            <%
                }
            %>
        </table>
        <h3>Įveskite norimus skaičius</h3>
        <form>
            nuo:<input name ="nuo" value="<%=nuo%>">
            iki:<input name="iki" value="<%=iki%>">
            <input type="submit" value="Vykdyti">
        </form>

    </body>
</html>
