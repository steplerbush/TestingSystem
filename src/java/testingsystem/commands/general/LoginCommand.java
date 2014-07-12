package testingsystem.commands.general;

import testingsystem.commands.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import testingsystem.controllers.FrontController;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.beans.SiteRole;
import testingsystem.model.beans.SiteUser;
import testingsystem.model.bl.UserBL;

/**
 * This command allows the user to log in.
 *
 */
public class LoginCommand implements ICommand {

    static {
        logger = Logger.getLogger(LoginCommand.class);
    }
    private static Logger logger;

    /**
     * Logins user in the system by checking his login and password and created
     * user's session.
     */
    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        if (RequestHelper.isAuthUser(request)) {
            logger.warn("An authorized user " + ((SiteUser)request.getSession()
                    .getAttribute(AttributesManager.ATTRIBUTE_USER)).getLogin()
                    + " tried to login " + request.getRemoteAddr());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.INDEX_PAGE_PATH);
        }
        String login = request.getParameter(
                AttributesManager.PARAM_NAME_LOGIN);
        String password = request.getParameter(
                AttributesManager.PARAM_NAME_PASSWORD);

        if (checkForEmptyFields(login, password, request)) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.LOGIN_ERROR_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIN_PAGE_PATH);
        }

        UserBL uBL = new UserBL(FrontController.getDAOFactory());
        SiteUser siteUser = uBL.getSiteUser(login, password);

        if (siteUser == null) { // incorrect login or password
            logger.warn("Unsuccessful attempt to log in with user name"
                    + " '" + login + "'" + ". RemoteAddr: "
                    + request.getRemoteAddr());
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.LOGIN_ERROR_MESSAGE);
            return ConfigurationManager.getInstance()
                    .getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        } else {
            SiteRole role = uBL.getSiteRole(siteUser);
            HttpSession session = request.getSession(true);
            if (role.getRoleName().equalsIgnoreCase(
                    AttributesManager.ADMIN_ROLE)) {
                logger.info("Administrator '" + login
                        + "' successfully logged into the system"
                        + ". RemoteAddr: " + request.getRemoteAddr());
                session.setAttribute(AttributesManager.ATTRIBUTE_USER_ROLE,
                        AttributesManager.ADMIN_ROLE);
                session.setAttribute(AttributesManager.ATTRIBUTE_USER,
                        siteUser);
                session.setAttribute(AttributesManager.ATTRIBUTE_LOGIN,
                        login);
            } else if (role.getRoleName().equalsIgnoreCase(
                    AttributesManager.TUTOR_ROLE)) {
                logger.info("Tutor '" + login
                        + "' successfully logged into the system"
                        + ". RemoteAddr: " + request.getRemoteAddr());
                session.setAttribute(AttributesManager.ATTRIBUTE_USER_ROLE,
                        AttributesManager.TUTOR_ROLE);
                session.setAttribute(AttributesManager.ATTRIBUTE_USER, 
                        uBL.getTutor(siteUser));
                session.setAttribute(AttributesManager.ATTRIBUTE_LOGIN,
                        login);
            } else {
                logger.info("Student '" + login
                        + "' successfully logged into the system"
                        + ". RemoteAddr: " + request.getRemoteAddr());
                session.setAttribute(AttributesManager.ATTRIBUTE_USER_ROLE,
                        AttributesManager.STUDENT_ROLE);
                session.setAttribute(AttributesManager.ATTRIBUTE_USER,
                        siteUser);
                session.setAttribute(AttributesManager.ATTRIBUTE_LOGIN,
                        login);
            }
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.INDEX_PAGE_PATH);
        }
    }

    public boolean checkForEmptyFields(String login, String password,
            HttpServletRequest request) {
        if (login == null || login.isEmpty()
                || password == null || password.isEmpty()) {
            logger.info("Unsuccessful attempt to log in."
                    + " Login or password is empty or null"
                    + ". RemoteAddr: " + request.getRemoteAddr());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "LoginCommand{" + '}';
    }
}
