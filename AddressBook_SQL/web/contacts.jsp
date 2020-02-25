<%-- 
    Document   : contacts
    Created on : Feb 3, 2020, 7:07:12 PM
    Author     : rimid
--%>


<%@page import="lt.bit.data22.Contact"%>
<%@page import="lt.bit.data22.DB"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacts</title>
    </head>
    <body>
        <style>
            /*Modal window*/
            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }

            /* The Close Button */
            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }
            /* The Close Button */
            /*            .closeE {
                            color: #aaaaaa;
                            float: right;
                            font-size: 28px;
                            font-weight: bold;
                        }
            
                        .closeE:hover,
                        .closeE:focus {
                            color: #000;
                            text-decoration: none;
                            cursor: pointer;
                        }*/

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

            h2 {
                margin-bottom: 30px;
            }

            h3 {
                margin-top: 50px;
                text-align: center;
                padding: 40px;
                color: green;
            }

            form {
                width: 100%;
                text-align: center;
                font-size: 13px;
                text-transform: uppercase;  
                color: blue;
            }

            .names {
                text-align: center;
                padding: 8px;
                background-color:blanchedalmond
            }

            .add {
                display: inline-block;
                line-height: 25px;
                padding-left: 30px;
                padding-right: 30px;
                margin-top: 30px;
                margin-left: 10px;
                font-size: 20px;
                background-color: greenyellow;
            }

            .btn {
                padding: 0;
            }
            .record-settings {
                border: none;
                margin: 0;
                width: 20%;
                text-align: right;
            }

            #record {
                display: inline-block;
                padding: 0;
                margin: 0;
                width: 40%;
                /*float: left;*/
            }

            .record {
                line-height: 25px;
                padding-left: 30px;
                padding-right: 30px;
                font-size: 20px;
                margin: 0;
                background-color: cornflowerblue;
            }

            .contact {
                line-height: 25px;
                padding-left: 30px;
                padding-right: 30px;
                font-size: 20px;
                margin-left: 10px;
                margin-right: 10px;
                text-decoration: none;
                background-color: gold;
            }
            button > a {
                text-decoration: none;
                color: black;
            }

            .contact-w {
                width: 20%;
            }

        </style>
    </head>
<body>

    <%
        Connection conn = (Connection) request.getAttribute("conn");
        Integer iddd = Integer.parseInt(request.getParameter("id"));
    %>

    <h1>Person <%=DB.getPerson(conn, iddd)%> addresses are: </h1>

    <table>
        <tr>
            <!--<th style="border: none;"></th>-->

            <th class="names">ID</th>
            <th class="names">Contact type</th>
            <th class="names">Contact</th>

        </tr>

        <% for (Contact a : DB.getPersonContacts(conn, iddd)) {
        %>
        <tr>
            <th><%=a.getId()%></th>
            <th><%=a.getType()%></th>
            <th><%=a.getContact()%></th>

            <th class="record-settings">
                <!--Edit record modal-->
                <!--Edit button for records-->
                <button class="record" id="<%=a.getId()%>">Edit</button>

                <!--The Modal--> 
                <div id="myModalE<%=a.getId()%>" class="modal">

                    <!--Modal content--> 
                    <div class="modal-content">
                        <span class="cancelE<%=a.getId()%> closeE<%=a.getId()%>"
                              style="
                              color: #aaaaaa;
                              float: right;
                              font-size: 28px;
                              font-weight: bold;


                              .closeE<%=a.getId()%>:hover,
                              .closeE<%=a.getId()%>:focus 
                              color: #000;
                              text-decoration: none;
                              cursor: pointer;
                              }"

                              >&times;</span>

                        <form method="POST" action="uppdatecontact">
                            <h2>Change selected data</h2>
                            <input type="hidden" value="<%=a.getId()%>" name="idd">
                            <input type="hidden" value="<%=iddd%>" name="id">
                            Contact type:<input name ="typ" value="<%=a.getType()%>">
                            Contact:<input name="con" value="<%=a.getContact()%>">
                            <input class="add" type="submit" value="Change">
                        </form>
                        <form style="text-align: right" method="POST" action="contacts.jsp">
                            <input type="hidden" value="<%=iddd%>" name="id">
                            <input  class="add" type="submit" value="Cancel">
                        </form>
                    </div>
                </div>
                <!--Delete button-->
                <form id="record" method="POST" action="removecontact" >
                    <input type="hidden" value="<%=a.getId()%>" name="ids">
                    <input type="hidden" value="<%=iddd%>" name="id">
                    <input class="record" type="submit" value="Delete">
                </form>
            </th>
        </tr>
        <script>
            // Modal for editing
            // Get the modal
            var modalE<%=a.getId()%> = document.getElementById("myModalE<%=a.getId()%>");
            // Get the button that opens the modal
            var btnE<%=a.getId()%> = document.getElementById("<%=a.getId()%>");
            // Get the <span> element that closes the modal
            var spanE<%=a.getId()%> = document.getElementsByClassName("closeE<%=a.getId()%>")[0];
            // Get the element that closes the modal
            var cancelE<%=a.getId()%> = document.getElementsByClassName("cancelE<%=a.getId()%>")[0];
            // When the user clicks the button, open the modal 
            btnE<%=a.getId()%>.onclick = function () {
                modalE<%=a.getId()%>.style.display = "block";
            };
            // When the user clicks on <span> (x), close the modal
            spanE<%=a.getId()%>.onclick = function () {
                modalE<%=a.getId()%>.style.display = "none";
            };
            // When the user clicks on button cancel, close the modal
            cancelE<%=a.getId()%>.onclick = function () {
                modalE<%=a.getId()%>.style.display = "none";
            };


        </script>

        <%
            }
        %>

    </table>

    <h3>Add new contact</h3>
    <!--Add new record modal-->
    <!-- Trigger/Open The Modal -->

    <form method="POST" action="index.jsp">
        <input class="add" id="myBtn" type="button" value="Add">
        <input class="add" type="submit" value="Back">
    </form>

    <!-- The Modal -->
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <form method="POST" action="addcontact">
                <h2>Insert new data</h2>
                Contact type:<input class="in" name ="typ" placeholder="Type">
                Contact:<input class="in" name="con" placeholder="Contact">
                <input type="hidden" value="<%=iddd%>" name="id">
                <input class="add" type="submit" value="Add">
                <input class="add cancel" type="reset" value="Cancel">

            </form
        </div>

    </div>
    <script>
        // Modal for adding
        // Get the modal
        var modal = document.getElementById("myModal");
        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");
        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];
        // Get the element that closes the modal
        var cancel = document.getElementsByClassName("cancel")[0];
        // When the user clicks the button, open the modal 
        btn.onclick = function () {
            modal.style.display = "block";
        };
        // When the user clicks on <span> (x), close the modal
        span.onclick = function () {
            modal.style.display = "none";
        };
        // When the user clicks on button cancel, close the modal
        cancel.onclick = function () {
            modal.style.display = "none";
        };

    </script>
</body>
</html>
