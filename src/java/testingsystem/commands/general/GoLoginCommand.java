/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.general;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import testingsystem.commands.ICommand;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.model.beans.SiteUser;

/**
 *
 * @author mirman
 */
public class GoLoginCommand implements ICommand {

    static {
        logger = Logger.getLogger(LoginCommand.class);
    }
    private static Logger logger;

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        if (RequestHelper.isAuthUser(request)) {
            logger.warn("An authorized user " + ((SiteUser) request.getSession()
                    .getAttribute(AttributesManager.ATTRIBUTE_USER)).getLogin()
                    + " tried to go to login page" + request.getRemoteAddr());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.INDEX_PAGE_PATH);
        } else {
            //logger
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }

}
