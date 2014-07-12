<div id="plainColumn">
    <t:errorTag errorMessage="${errorMessage}"></t:errorTag>
    <t:okMessageTag okMessage="${okMessage}"></t:okMessageTag>
    <c:choose>
        <c:when test="${user.isApproved() == false}">
            <h4>You can't do anything before you will not be confirmed by the site administration. 
                    If for one day, you have not been confirmed by the administrator, 
                    you can send an email to the address written at the bottom of the site asking to make this faster.</h4>
        </c:when>
        <c:otherwise>
            <h4>Here is the list of all your tests</h4>
            <a href="site?action=gocreatetest">Create new test</a>
        </c:otherwise>
    </c:choose>
</div>