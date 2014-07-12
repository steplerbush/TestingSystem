<div id="plainColumn">
    <h2>Admin Page</h2>
    <t:errorTag errorMessage="${errorMessage}"></t:errorTag>
    <t:okMessageTag okMessage="${okMessage}"></t:okMessageTag>
        <div id="admDiv">
            <h3>Create new admin:</h3>
            <form action="site?action=addadmin" method=post>
                <table cellpadding="5" align="center">
                    <tr>
                        <td align="center">
                            Login
                        </td>
                        <td align="center">
                            Password
                        </td>
                        <td align="center">
                            First Name
                        </td>
                        <td align="center">
                            Second Name
                        </td>
                        <td align="center">
                            Email
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" name="admlogin" size="20" value="${requestScope.admlogin}"/>
                    </td>
                    <td>
                        <input type="text" name="admpassword" size="20" value="${requestScope.admpassword}"/>
                    </td>
                    <td>
                        <input type="text" name="admfirstname" size="20" value="${requestScope.admfirstname}"/>
                    </td>
                    <td>
                        <input type="text" name="admsecondname" size="20" value="${requestScope.admsecondname}"/>
                    </td>
                    <td>
                        <input type="text" name="admemail" size="20" value="${requestScope.admemail}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" align="center">
                        <input type="submit" value="Create">
                        <input type="reset" value="Reset">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="studDiv">
        <h3>Show/Remove student groups:</h3>
        <c:choose>
            <c:when test="${selectedgroup == null}">
                <form action="site?action=showstudentgroup" method=post>
                    <select name="studentgroup" size="1">
                        <c:forEach items="${groupslist}" var="group">
                            <option value="${group.getId()}">${group}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Show">
                </form>
            </c:when>
            <c:otherwise> 
                <h4>${selectedgroup}</h4>
                <c:choose>
                    <c:when test="${studentsselgr == null}">
                        <p>This group is empty</p>
                    </c:when>
                    <c:otherwise>
                        <table align="center">
                            <tr>
                                <td>Student's Login</td>
                                <td>First Name</td>
                                <td>Second Name</td>
                                <td>Email</td>
                            </tr>
                            <c:forEach items="${studentsselgr}" var="student">
                                <tr>
                                    <td>${student.getUser().getLogin()}</td>
                                    <td><c:out value="${student.getUser().getFirstName()}" /></td>
                                    <td>${student.getUser().getSecondName()}</td>
                                    <td>${student.getUser().getEmail()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                        <red><h4>Note, that removing this group will send all it's students to the default group</h4></red>
                        </c:otherwise>
                    </c:choose>
                <a href="site?action=delstudentgroup">Remove this group</a>
                <pp>     </pp>
                <a href="site?action=adminclearsession"> Return</a>
            </c:otherwise>
        </c:choose>
        <hr>
    </div>
    <div id="studDiv">
        <h3>Create new student group:</h3>
        <form action="site?action=addstudentgroup" method=post>
            <table cellpadding="5" align="center">
                <tr>
                    <td align="center">
                        Group name
                    </td>
                    <td align="center">
                        Group number
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="groupname" size="20" value="${requestScope.groupname}"/>
                    </td>
                    <td>
                        <input type="number" name="groupnumber" size="25" value="${requestScope.groupnumber}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Create">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="tutDiv">
        <h3>New registered tutors to approve:</h3>
        <c:if test="${uncheckedtutor != null}">
            <div id=""><t:showTutorInfoTag tutor="${uncheckedtutor}"/></div>
            <p><a href="site?action=tutorapprove">Approve selected tutor</a></p>
        </c:if>
        <form action="site?action=tutorshow" method=post>
            <select name="seltutor" size="1" >
                <c:forEach items="${uncheckedtutors}" var="tutor">
                    <option value="${tutor.getId()}">${tutor.getUser().getFirstName()}   ${tutor.getUser().getSecondName()}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Choose">
        </form>
    </div>
</div>

