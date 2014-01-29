<div id="indexLeftColumn">
    <div id="welcomeText">
        <h1>About Online Examination System(OES)</h1>
        <p>Online Examination System(OES) is a Multiple Choice Questions(MCQ) based examination system that provides an easy to use environment for both Test Conducters and Students appearing for Examination. The main objective of OES is to provide all the features that an Examination System must have, with the &quot;interfaces that doesn't Scare it's Users!&quot;.
        <h1>Taxonomy of OES</h1>
        <p>Users of OES are classified into three categories: Administrators, Test Conductors and Students. Administrators are responsible for management of system users, subjects, tests, questions, results, system backup and recovery , etc. Test conductors are responsible for preparing subjects, tests and questions. Students are the candidates appearing for the Examination.   </p>
    </div>
</div>
<div id="indexDivider"></div>
<div id="indexRightColumn">
    <c:choose>
        <c:when test="${user == null}">
            <p> Login Here </p>
            <form action="site?action=submitlogin" method=post>
                <p><strong>Please enter Your User Name: </strong>
                    <input type="text" name="login" size="25">
                <p><p><strong>Please enter Your Password: </strong>
                    <input type="password" size="25" name="password">
                <p><p>
                    <input type="submit" value="Submit">
                    <pp>     </pp>
                    <input type="reset" value="Reset">
                <p></p>     
            </form>
            <t:goregisterTag/>
        </c:when>
        <c:otherwise>
            <t:loginTag siteuser="${user}"></t:loginTag>
        </c:otherwise>
    </c:choose>
</div>