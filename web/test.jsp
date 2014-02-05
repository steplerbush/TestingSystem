<%@page import="testingsystem.model.bl.TutorBL"%>
<%@page import="testingsystem.controllers.FrontController"%>
<%@page import="testingsystem.model.bl.UserBL"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript"
src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<% session.setAttribute("list", new TutorBL(FrontController.getDAOFactory()).getUnCkeckedTutors());
%>


<html>
    <head>
        <title>Updatable Collections</title>
    </head>

    <body>
        <form action="sadf">
        <select id="aaa" name="items" onchange="this.form.submit()">
            <c:forEach items="${list}" var="selectedPerson">
                <c:set var="pers" value="${selectedPerson}" />
                <option value="${selectedPerson}">${selectedPerson}</option>
            </c:forEach>
        </select>
        <input name="sss" id="www" type="text"/>
        <script type="text/javascript">
            function showSelectedCustomer(dropdown) {
                var selectedCustomer = dropdown.options[dropdown.selectedIndex].value;
                var currentCustomer = document.getElementById('www');
                currentCustomer.firstChild.nodeValue = selectedCustomer.toString();
            }
        </script>
        </form>
    </body>
</html>
