<div id="plainColumn">
    <t:errorTag errorMessage="${errorMessage}"></t:errorTag>
        <p>Please enter a user name and password that is authorized to access this site.</p>
        <p> Login Here </p>
        <form action="site?action=submitlogin" method=post>
            <div id="registerTable">
                <div id="registerLine">
                    <div id="registerLineText">
                        User Name:
                    </div>
                    <div id="registerLineField">
                        <input type="text" name="login" size="25">
                    </div>
                </div>
                <div id="registerLine">
                    <div id="registerLineText">
                        Password:
                    </div>
                    <div id="registerLineField">
                        <input type="password" size="25" name="password">
                    </div>
                </div>
                <input type="submit" value="Submit">
                <pp>     </pp>
                <input type="reset" value="Reset">
            </div>
        </form>
    <t:goregisterTag/>
</div>
