<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*,p1.Employee" %>
        <% if(session.getAttribute("name")==null) { response.sendRedirect("Login.html"); } %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Employee Database</title>
                <!-- Favicon-->
                <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
                <!-- Bootstrap icons-->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
                    rel="stylesheet" />
                <!-- Core theme CSS (includes Bootstrap)-->
                <link href="css/styles.css" rel="stylesheet" />
                <style>
                    .bg-color {
                        background-color: hsl(310, 92%, 51%);
                    }
                    img.inf{
                height: 70px;
                width: 70px;
                margin-left:20px;
                border-top-left-radius: 15%;
                border-bottom-right-radius: 15%;
                box-shadow: 4px 4px 8px #070402;
            }

                </style>
            </head>

            <body class="d-flex flex-column h-100">
                <main class="flex-shrink-0">
                    <!-- Navigation-->
                    <nav class="navbar navbar-expand-lg navbar-dark bg-color">
                        <div class="container px-5">

                            <h1><i>Employee Details</i></h1>

                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation"><span
                                    class="navbar-toggler-icon"></span></button>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                                    <li class="nav-item mx-0 mx-lg-1 "><a
                                            class="nav-link py-3 px-0 px-lg-3 rounded bg-warning text-dark"
                                            href="index.html"><b>Home</b></a></li>
                                    <li class="nav-item mx-0 mx-lg-1 "><a
                                            class="nav-link py-3 px-0 px-lg-3 rounded bg-warning text-dark"
                                            href="about.html"><b>About</b></a></li>
                                    <li class="nav-item mx-0 mx-lg-1 "><a
                                            class="nav-link py-3 px-0 px-lg-3 rounded bg-warning text-dark"
                                            href="contact.html"><b>Contact</b></a></li>

                                    <li class="nav-item mx-0 mx-lg-1 "><a
                                            class="nav-link py-3 px-0 px-lg-3 rounded bg-warning text-dark" href="#">
                                           <b> <%=session.getAttribute("name")%></b>
                                        </a></li>
                                    <li class="nav-item mx-0 mx-lg-1 "><a
                                            class="nav-link py-3 px-0 px-lg-3 rounded bg-warning text-dark"
                                            href="Login.html"><b>LOGOUT</b></a></li>
                                    <li class="nav-item mx-0 mx-lg-1">
                                         <img class="inf" src='<%=session.getAttribute("img")%>' alt="image" />
                                    </li>  
                                </ul>
                            </div>
                        </div>
                    </nav>
                    <!-- Header-->
                    <div class="container">
                        <center>
                            <h2>Employee information</h2>
                            <table class="table table-bordered table-striped">
                                <tr class="shadow p-3 mb-3 bg-white rounded">
                                    <th>Employee id</th>
                                    <th>Employee name</th>
                                    <th>Employee addr</th>
                                    <th>Employee salary</th>
                                    <th>Employee email</th>
                                    <th>ACTION</th>
                                </tr>

                                <c:forEach var="Employee" items="${allEmployee}">
                                    <tr class="shadow p-3 mb-3 bg-white rounded">
                                        <td>
                                            <c:out value="${Employee.id}" />
                                        </td>
                                        <td>
                                            <c:out value="${Employee.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${Employee.address}" />
                                        </td>
                                        <td>
                                            <c:out value="${Employee.salary}" />
                                        </td>
                                        <td>
                                            <c:out value="${Employee.email}" />
                                        </td>

                                        <c:set var="message" value='${requestScope["umail"]}' />
                                        <c:set var="reqEmail" value="${Employee.email}" scope="request" />



                                        <c:choose>
                                            <c:when test="${reqEmail == message}">
                                                <td style="align-items: center;">&nbsp;&nbsp;<a
                                                        href=Update.jsp?id=<c:out value='${Employee.id}' />&name=<c:out value='${Employee.name}' />&addr=<c:out value='${Employee.address}' />&sal=<c:out value='${Employee.salary}' />&email=<c:out value='${Employee.email}' />><button type="button" class="btn btn-primary"><b>Update</b></button> </a>
                                                    &nbsp;&nbsp;&nbsp; <a href=servlet4?idss=<c:out value="${Employee.id}" />><button type="button" class="btn btn-danger"><b>Delete</b></button></a>
                                                </td>
                                            </c:when>

                                            <c:otherwise>
                                                <td>
                                                    <button class="btn btn-danger" type="button">No Access</button>
                                                </td>

                                            </c:otherwise>
                                        </c:choose>











                                    </tr>
                                </c:forEach>

                            </table>
                        </center>
                    </div>

                    <!-- Features section-->

                    <!-- Bootstrap core JS-->
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
                    <!-- Core theme JS-->
                    <script src="js/scripts.js"></script>
            </body>

            </html>