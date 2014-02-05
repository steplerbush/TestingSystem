<%-- 
    Document   : okMessageTag
    Created on : Jan 31, 2014, 2:40:46 AM
    Author     : mirman
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="okMessage"  required="true" rtexprvalue="true" type="java.lang.String" %>

<%-- any content can be specified here e.g.: --%>
<c:if test="${okMessage != null}">
    <green><h4>${okMessage}</h4></green>
</c:if>