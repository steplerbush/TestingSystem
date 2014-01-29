<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@ attribute name="siteuser"  required="true" rtexprvalue="true" type="testingsystem.model.beans.SiteUser" %>

<p><strong>${siteuser.getFirstName()} ${siteuser.getSecondName()},</strong></p>
<p>You are already authorized</p>
<a href="site?action=home">Go to your's home page</a>