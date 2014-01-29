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
public class GoLoginCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute(AttributesManager.ATTRIBUTE_USER) != null) {
            //logger
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        } else {
            //logger
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }

}
