
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="authorName" required="true" type="java.lang.String"%>
<%@attribute name="authorEmail" required="true" type="java.lang.String"%>

<%-- any content can be specified here e.g.: --%>
<h5>
    Copyright © ${authorName} <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> | <a href="mailto:${authorEmail}">${authorEmail}</a>
</h5>