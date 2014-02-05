<%-- 
    Document   : newtag_file
    Created on : Jan 29, 2014, 9:47:35 PM
    Author     : mirman
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="errorMessage"  required="true" rtexprvalue="true" type="java.lang.String" %>

<%-- any content can be specified here e.g.: --%>
<c:if test="${errorMessage != null}">
    <red><h3>${errorMessage}</h3></red>
</c:if>