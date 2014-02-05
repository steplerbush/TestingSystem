<%-- 
    Document   : showTutorInfoTag
    Created on : Jan 30, 2014, 9:23:35 PM
    Author     : mirman
--%>

<%@tag description="shows user and tutor info in table" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="tutor" 
             required="true" 
             rtexprvalue="true" 
             type="testingsystem.model.beans.Tutor"%>

<%-- any content can be specified here e.g.: --%>
<table align="center" border="1">
    <tr>
        <td>
            First Name:
        </td>
        <td>
            ${tutor.getUser().getFirstName()}
        </td>
    </tr>
    <tr>
        <td>
            Second Name:
        </td>
        <td>
            ${tutor.getUser().getSecondName()}
        </td>
    </tr>
    <tr>
        <td>
            Email:
        </td>
        <td>
            ${tutor.getUser().getEmail()}
        </td>
    </tr>
    <tr>
        <td>
            Login:
        </td>
        <td>
            ${tutor.getUser().getLogin()}
        </td>
    </tr>
    <tr>
        <td>
            Telephone:
        </td>
        <td>
            ${tutor.getTelephone()}
        </td>
    </tr>
    <tr>
        <td>
            Info:
        </td>
        <td>
            <pp>${tutor.getInfo()}</pp>
        </td>
    </tr>
</table>
