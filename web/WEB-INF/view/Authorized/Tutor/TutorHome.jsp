<div id="plainColumn">
    [tutor home]
        <% HttpSession ses = request.getSession();
        Integer userid = (Integer) ses.getAttribute("userid");
        out.println(userid);
        String userrole = (String) ses.getAttribute("userrole");
        out.println(userrole);%>
</div>