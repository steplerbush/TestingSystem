<div id="plainColumn">
    <p>Your test:</p>
    <table align="center" border="1">
        <tr>
            <td>
                Title
            </td>
            <td>
                ${temptest.getLocale().getTitle()}
            </td>
        </tr>
        <tr>
            <td>
                Description
            </td>
            <td>
                ${temptest.getLocale().getDescription()}
            </td>
        </tr>
        <tr>
            <td>
                Opening time
            </td>
            <td>
                ${temptest.getOpenTime()}
            </td>
        </tr>
        <tr>
            <td>
                Closing time
            </td>
            <td>
                ${temptest.getCloseTime()}
            </td>
        </tr>
        <tr>
            <td>
                Duration
            </td>
            <td>
                ${temptest.getDuration()}
            </td>
        </tr>
        <tr>
            <td>
                Language
            </td>
            <td>
                ${temptest.getLocale().getLang()}
            </td>
        </tr>
    </table>
    <c:if test="${questionlist == null}">
        <p><strong>There are no questions in this test. You must to add at least one question to save this test!</strong></p>
    </c:if>
    <p>Adding new question:</p>
    <table align="center">
        <tr>
            <td>
                Question
            </td>
            <td>
                #
                <c:choose>
                    <c:when test="${questionlist == null}">
                        1
                    </c:when>
                    <c:otherwise>
                        ${questionlist.size()+1}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                
            </td>
            <td>
                
            </td>
        </tr>
    </table>
</div>