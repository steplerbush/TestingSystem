/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.general;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import testingsystem.commands.ICommand;
import testingsystem.controllers.FrontController;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.beans.SiteUser;
import testingsystem.model.beans.Tutor;
import testingsystem.model.bl.AdminBL;
import testingsystem.model.bl.UserBL;

/**
 *
 * @author mirman
 */
public class HomeCommand implements ICommand {

    static {
        logger = Logger.getLogger(LoginCommand.class);
    }
    private static Logger logger;

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        if (!RequestHelper.isAuthUser(request)) {
            logger.warn("An unauthorized user "
                    + ((SiteUser) request.getSession()
                    .getAttribute(AttributesManager.ATTRIBUTE_USER)).getLogin()
                    + " tried to go to home page" + request.getRemoteAddr());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIN_PAGE_PATH);
        }
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(
                AttributesManager.ATTRIBUTE_USER_ROLE);
        switch (role) {
            case AttributesManager.ADMIN_ROLE: {
                return setAdminHome(session);
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
                logger.warn("Unsuccessful attempt to go to a homepage. User "
                        + " '" + ((SiteUser) request.getSession()
                        .getAttribute(AttributesManager.ATTRIBUTE_USER))
                        .getLogin() + "'" + ". RemoteAddr: "
                        + request.getRemoteAddr());
                request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.GO_HOME_ERROR_MESSAGE);
                return ConfigurationManager.getInstance().
                        getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            }
        }
    }

    private String setAdminHome(HttpSession session) {
        UserBL u = new UserBL(FrontController.getDAOFactory());
        AdminBL tbl = new AdminBL(FrontController.getDAOFactory());
        session.setAttribute(AttributesManager.ATTRIBUTE_GROUPS_LIST, 
                u.getGroupList());
        List<Tutor> tutors = tbl.getUnCkeckedTutors();
        session.setAttribute(AttributesManager.ATTRIBUTE_UNCHECKED_TUTORS, tutors);
        //removing possible attributes:
        session.removeAttribute(null);
        //logger
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.ADMIN_HOME_PATH);
    }

}
