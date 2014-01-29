<div id="plainColumn">
    <p></p><p>Registration</p><p></p>
    <c:choose>
        <c:when test="${reguser == null}">
            <c:if test="${errorMessage != null}">
                <red><h3>${errorMessage}</h3></red>
                    </c:if>
            <form action="site?action=continueregister" method=post>
                <div id="registerTable">
                    <div id="registerLine">
                        <div id="registerLineText">
                            First Name:
                        </div>
                        <div id="registerLineField">
                            <input type="text" name="firstname" size="25" value="${requestScope.firstname}">
                        </div>
                    </div>
                    <div id="registerLine">
                        <div id="registerLineText">
                            <div id=text>Second Name:</div>
                        </div>
                        <div id="registerLineField">
                            <input type="text" size="25" name="secondname" value="${requestScope.secondname}">
                        </div>
                    </div>
                    <div id="registerLine">
                        <div id="registerLineText">
                            Email:
                        </div>
                        <div id="registerLineField">
                            <input type="email" size="25" name="email" value="${requestScope.email}">
                        </div>
                    </div>
                    <div id="registerLine">
                        <div id="registerLineText">
                            Login
                        </div>
                        <div id="registerLineField">
                            <input type="text" size="25" name="login" value="${requestScope.login}">
                        </div>
                    </div>
                    <div id="registerLine">
                        <div id="registerLineText">
                            Password:
                        </div>
                        <div id="registerLineField">
                            <input type="text" size="25" name="password" value="${requestScope.password}">
                        </div>
                    </div>
                    <div id="registerLine">
                        <div id="registerLineText">
                            Role:
                        </div>
                        <div id="registerLineField">
                            Student   
                            <INPUT TYPE="radio" NAME="roleradios" VALUE="Student" CHECKED>
                        </div>
                        <div id="registerLineField">
                            Tutor  
                            <INPUT TYPE="radio" NAME="roleradios" VALUE="Tutor">
                        </div>
                    </div>
                    <input type="submit" value="Submit">
                    <pp>     </pp>
                    <input type="reset" value="Reset">
                </div>    
            </form>
        </c:when>
        <c:otherwise>
            <form action="site?action=submitregister" method=post>
                <div id="registerTable">
                    <t:showUserInfoTag user="${reguser}" role="${reguserrole}">
                    </t:showUserInfoTag>
                    <c:choose>
                        <c:when test="${reguserrole == 'Student'}">
                            <div id="registerLine">
                                <div id="registerLineText">
                                    Group:
                                </div>
                                <div id="registerLineField">
                                    <select name="studentgroup" size="1">
                                        <c:forEach items="${groupslist}" var="group">
                                            <option value="${group}">${group}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div id="registerLine">
                                <div id="registerLineText">
                                    <strong>Tel:</strong>
                                </div>
                                <div id="registerLineField">
                                    <input type="tel" name="telephone">
                                </div>
                            </div>
                            <div id="registerLine">
                                <strong>Information about you:</strong>
                            </div>
                            <div id="registerLine">
                                <textarea name="info" cols="40" rows="5" ></textarea>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <input type="submit" value="Submit">
                    <pp>     </pp>
                    <a href="BackRegister" >Go Back</a>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
    <br><br>
</div>
