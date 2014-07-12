<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ attribute name="username"  required="true" rtexprvalue="true" type="java.lang.String" %>

<p><strong>${username}, </strong></p>
<p>You are already authorized</p>
<a href="site?action=home">Go to your's home page</a>