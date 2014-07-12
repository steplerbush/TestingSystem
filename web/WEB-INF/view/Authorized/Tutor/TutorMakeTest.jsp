z<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.js"></script>
<div id="plainColumn">
    <h3>Test Creation</h3>
    <t:errorTag errorMessage="${errorMessage}"/>
    <t:okMessageTag okMessage="${okMessage}"/>
    <p>Please choose the first language for your test:</p>
    <form action="site?action=contintestcreation" method=post>
        <select name="selectLang" size="1" >
            <c:forEach items="${langlist}" var="lang">
                <option value="${lang.value}">${lang.value}</option>
            </c:forEach>
        </select>
        <p></p>
        <table align="center">
            <tr>
                <td>
                    Set the title of test:
                </td>
                <td>
                    <input type="text" name="testTitle" value="${testTitle}" size="80" maxlength="150">
                </td>
            </tr>
            <tr>
                <td>
                    <p>Set the opening time:</p>    
                </td>
                <td>
                    <input id="openingtimepicker" name="openingtime" type="text" value = "<fmt:formatDate value="${openingtime}" pattern="dd-MM-yyyy HH:mm" />"/>
                    <script>
                        $('#openingtimepicker').datetimepicker({
                            mask: true,
                            format: 'd-m-Y H:i' // '9999/19/39 29:59' - digit is the maximum possible for a cell
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Set the closing time:</p>    
                </td>
                <td>
                    <input id="closingtimepicker" name="closingtime" type="text" value = "<fmt:formatDate value="${closingtime}" pattern="dd-MM-yyyy HH:mm" />"/>
                    <script>
                        $('#closingtimepicker').datetimepicker({
                            mask: true,
                            format: 'd-m-Y H:i' // '9999/19/39 29:59' - digit is the maximum possible for a cell
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Set the duration of test:</p>    
                </td>
                <td>
                    <input id="timepicker" name="duration" type="text" value = "${duration}"/>
                    <script>
                        $('#timepicker').datetimepicker({
                            datepicker: false,
                            mask: true,
                            format: 'H:i:s'
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <td>
                    Set the description of test:
                </td>
                <td>
                    <textarea name="testdescription" cols="90" rows="6" draggable="false">${testdescription}</textarea>
                </td>
            </tr>
        </table>
        <input type="submit" value="Continue test creation">
        <a href="site?action=contintestcreation">Continue test creation</a>
    </form>
    <p></p>
</div>