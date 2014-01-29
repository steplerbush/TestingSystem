/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import testingsystem.commands.ICommand;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;

/**
 *
 * @author mirman
 */
public class GoRegisterCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute(AttributesManager.ATTRIBUTE_USER) != null) {
                session.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE, 
                        MessageManager.ALREADY_LOGGEDIN_ERROR_PATH);
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
            //???
            session.invalidate();
        }
        return ConfigurationManager.getInstance()
                .getProperty(ConfigurationManager.REGISTER_PAGE_PATH);
    }

}
