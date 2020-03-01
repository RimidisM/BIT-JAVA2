<%-- 
    Document   : index
    Created on : Jan 30, 2020, 7:50:42 PM
    Author     : rimid
--%>



<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="lt.bit.data22.Person"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Persons</title>
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
                padding: 20px;
                color: blue;
            }

            .names {
                text-align: center;
                padding: 8px;
                background-color:blanchedalmond
            }

            .add {
                line-height: 25px;
                padding-left: 30px;
                padding-right: 30px;
                margin-top: 30px;
                font-size: 20px;
                background-color: greenyellow;
            }

            button {
                margin-left: calc((95%-800px)/2);
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
                cursor: pointer;
            }
            .record-contact {
                line-height: 25px;
                padding-left: 30px;
                padding-right: 30px;
                font-size: 20px;
                margin-left: 20px;
                background-color: gold;
                cursor: pointer;
            }

            .contact {
                display: inline-block;
                line-height: 25px;
                font-size: 20px;
                padding: 0;
                margin-left: 20px;
                text-decoration: none;
                bolor: gold;
                width: 40%;
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
        <h1>INFO ABOUT PEOPLE</h1>

        <table>
            <tr>
                <!--<th style="border: none;"></th>-->

                <th class="names">ID</th>
                <th class="names">First Name</th>
                <th class="names">Last Name</th>
                <th class="names">Birthday</th>
                <th class="names">Salary Eur</th>
                <th class="names">Contacts</th>

            </tr>

            <%
                EntityManager em = (EntityManager) request.getAttribute("em");
                Query q = em.createQuery("select p from Person p order by p.id");
                List<Person> l = q.getResultList();

                for (Person p : l) {
            %>
            <tr>
                <th><%=p.getId()%></th>
                <th><%=p.getFirstName()%></th>
                <th><%=p.getLastName()%></th>
                <th><%=p.getBirthDate()%></th>
                <th><%=p.getSalary()%></th>
                <th class="contact-w">
                    <form class="contact" method="POST" action="address.jsp">
                        <input type="hidden" value="<%=p.getId()%>" name="id" >
                        <input class="record-contact" type="submit" value="Addresses">
                    </form>
                    <form class="contact" method="POST" action="contacts.jsp">
                        <input type="hidden" value="<%=p.getId()%>" name="id" >
                        <input class="record-contact" type="submit" value="Contacts">
                    </form>

                </th>
                <th class="record-settings">
                    <!--Edit record modal-->
                    <!--Edit button for records-->
                    <button class="record" id="<%=p.getId()%>">Edit</button>

                    <!--The Modal--> 
                    <div id="myModalE<%=p.getId()%>" class="modal">


                        <!--Modal content--> 
                        <div class="modal-content">
                            <span class="cancelE<%=p.getId()%> closeE<%=p.getId()%>"
                                  style="
                                  color: #aaaaaa;
                                  float: right;
                                  font-size: 28px;
                                  font-weight: bold;


                                  .closeE<%=p.getId()%>:hover,
                                  .closeE<%=p.getId()%>:focus 
                                  color: #000;
                                  text-decoration: none;
                                  cursor: pointer;
                                  }"

                                  >&times;</span>
                            <form method="POST" action="edit">
                                <h2>Change selected data</h2>
                                <input type="hidden" value="<%=p.getId()%>" name="id">
                                Name:<input name ="na" value="<%=p.getFirstName()%>">
                                Surname:<input name="sn" value="<%=p.getLastName()%>">
                                Birth day:<input name="bd" value="<%=p.getBirthDate()%>">
                                Salary:<input name="sa" value="<%=p.getSalary()%>">
                                <input class="add" type="submit" value="Change"  >
                                <input class="add cancelE<%=p.getId()%>" type="submit" value="Cancel">
                            </form>
                        </div>
                    </div>
                    <!--Delete button-->
                    <form id="record" method="POST" action="remove">
                        <input type="hidden" value="<%=p.getId()%>" name="id">
                        <input class="record" type="submit" value="Delete">
                    </form>
                </th>
            </tr>
            <script>
                // Modal for editing
// Get the modal
                var modalE<%=p.getId()%> = document.getElementById("myModalE<%=p.getId()%>");
// Get the button that opens the modal
                var btnE<%=p.getId()%> = document.getElementById("<%=p.getId()%>");
// Get the <span> element that closes the modal
                var spanE<%=p.getId()%> = document.getElementsByClassName("closeE<%=p.getId()%>")[0];
// Get the element that closes the modal
                var cancelE<%=p.getId()%> = document.getElementsByClassName("cancelE<%=p.getId()%>")[0];
// When the user clicks the button, open the modal 
                btnE<%=p.getId()%>.onclick = function () {
                    modalE<%=p.getId()%>.style.display = "block";
                };
// When the user clicks on <span> (x), close the modal
                spanE<%=p.getId()%>.onclick = function () {
                    modalE<%=p.getId()%>.style.display = "none";
                };
// When the user clicks on button cancel, close the modal
                cancelE<%=p.getId()%>.onclick = function () {
                    modalE<%=p.getId()%>.style.display = "none";
                };

            </script>

            <%
                }
            %>

        </table>

        <h3>Add new record</h3>
        <!--Add new record modal-->
        <!-- Trigger/Open The Modal -->
        <button class="add" id="myBtn">Add</button>

        <!-- The Modal -->
        <div id="myModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <form method="POST" action="add">
                    <h2>Insert new data</h2>
                    Name:<input class="in" name ="na" placeholder="Name">
                    Surname:<input class="in" name="sn" placeholder="Surname">
                    Birth date:<input class="in" name="bd" placeholder="1900-01-02">
                    Salary:<input class="in" name="sa" placeholder="100">
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
