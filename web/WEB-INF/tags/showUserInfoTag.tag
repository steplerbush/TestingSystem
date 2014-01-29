<%-- 
    Document   : showUserInfoTag
    Created on : Jan 26, 2014, 5:46:35 PM
    Author     : mirman
--%>

<%@tag description="shows user info in table" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="user" 
             required="true" 
             rtexprvalue="true" 
             type="testingsystem.model.beans.SiteUser"%>
<%@attribute name="role" 
             required="true" 
             rtexprvalue="true"%>

<%-- any content can be specified here e.g.: --%>
<div id="registerLine">
    <div id="registerLineText">
        First Name:
    </div>
    <div id="registerLineField">
        ${user.getFirstName()}
    </div>
</div>
<div id="registerLine">
    <div id="registerLineText">
        Second Name:
    </div>
    <div id="registerLineField">
        ${user.getSecondName()}
    </div>
</div>
<div id="registerLine">
    <div id="registerLineText">
        Email:
    </div>
    <div id="registerLineField">
        ${user.getEmail()}
    </div>
</div>
<div id="registerLine">
    <div id="registerLineText">
        Login:
    </div>
    <div id="registerLineField">
        ${user.getLogin()}
    </div>
</div>
<div id="registerLine">
    <div id="registerLineText">
        Role:
    </div>
    <div id="registerLineField">
        ${role}
    </div>
</div>
