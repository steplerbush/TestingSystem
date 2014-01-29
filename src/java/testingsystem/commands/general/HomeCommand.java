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

/**
 *
 * @author mirman
 */
public class HomeCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(
                AttributesManager.ATTRIBUTE_USER_ROLE);
        switch (role) {
            case AttributesManager.ADMIN_ROLE: {
                //logger
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ADMIN_HOME_PATH);
            }
            case AttributesManager.TUTOR_ROLE: {
                //logger
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.TUTOR_HOME_PATH);
            }
            case AttributesManager.STUDENT_ROLE: {
                //logger
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.STUDENT_HOME_PATH);
            }
            default: {
                //logger
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
    }

}
