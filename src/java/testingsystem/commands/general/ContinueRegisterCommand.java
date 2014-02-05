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
import testingsystem.model.beans.StudentGroup;
import testingsystem.model.bl.UserBL;

/**
 *
 * @author mirman
 */
public class ContinueRegisterCommand implements ICommand {

    
    static {
        logger = Logger.getLogger(LoginCommand.class);
    }
    private static Logger logger;
    
    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        if (RequestHelper.isAuthUser(request)) {
            logger.warn("An authorized user " + ((SiteUser)request.getSession()
                    .getAttribute(AttributesManager.ATTRIBUTE_USER)).getLogin()
                    + " tried to register"
                    + request.getRemoteAddr());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.INDEX_PAGE_PATH);
        }
        HttpSession session = request.getSession(true);
        //if there is any empty field in the form
        if (request.getParameter(AttributesManager.PARAM_NAME_FIRSTNAME)
                .equals(AttributesManager.EMPTY_STRING)
                || request.getParameter(AttributesManager.PARAM_NAME_SECONDNAME)
                .equals(AttributesManager.EMPTY_STRING)
                || request.getParameter(AttributesManager.PARAM_NAME_EMAIL)
                .equals(AttributesManager.EMPTY_STRING)
                || request.getParameter(AttributesManager.PARAM_NAME_LOGIN)
                .equals(AttributesManager.EMPTY_STRING)
                || request.getParameter(AttributesManager.PARAM_NAME_PASSWORD)
                .equals(AttributesManager.EMPTY_STRING)) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.REGISTER_EMPTY_ERROR);
            reuseInputtedFields(request);

        } else { //if there is already registred user with such login or email
            UserBL uBL = new UserBL(FrontController.getDAOFactory());
            String test = uBL.testRegisterUser(
                    request.getParameter(AttributesManager.PARAM_NAME_EMAIL),
                    request.getParameter(AttributesManager.PARAM_NAME_LOGIN));
            if (test.equals(AttributesManager.DAO_MESSAGE_LOGIN_EXISTS)) {
                request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.REGISTER_LOGIN_EXISTS_ERROR);
                reuseInputtedFields(request);
            }
            if (test.equals(AttributesManager.DAO_MESSAGE_EMAIL_EXISTS)) {
                request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.REGISTER_EMAIL_EXISTS_ERROR);
                reuseInputtedFields(request);
            }
            if (test.equals(AttributesManager.EMPTY_STRING)) {
                SiteUser siteUser = uBL.createTempUser(
                        request.getParameter(
                                AttributesManager.PARAM_NAME_FIRSTNAME),
                        request.getParameter(
                                AttributesManager.PARAM_NAME_SECONDNAME),
                        request.getParameter(
                                AttributesManager.PARAM_NAME_EMAIL),
                        request.getParameter(
                                AttributesManager.PARAM_NAME_LOGIN),
                        request.getParameter(
                                AttributesManager.PARAM_NAME_PASSWORD));
                session.setAttribute(
                        AttributesManager.ATTRIBUTE_REG_USER, siteUser);
                request.removeAttribute(
                        AttributesManager.ATTRIBUTE_ERROR_MESSAGE);
                session.setAttribute(AttributesManager.ATTRIBUTE_REG_USER_ROLE,
                        request.getParameter(
                                AttributesManager.PARAM_NAME_REG_USER_ROLE));
                if (request.getParameter(
                        AttributesManager.PARAM_NAME_REG_USER_ROLE)
                        .equals(AttributesManager.STUDENT_ROLE)) {
                    List<StudentGroup> list = uBL.getGroupList();
                    request.setAttribute(
                            AttributesManager.ATTRIBUTE_GROUPS_LIST, list);
                }
            }
        }
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.REGISTER_PAGE_PATH);
    }

    private void reuseInputtedFields(HttpServletRequest request) {
        request.setAttribute(AttributesManager.PARAM_NAME_FIRSTNAME,
                request.getParameter(AttributesManager.PARAM_NAME_FIRSTNAME));
        request.setAttribute(AttributesManager.PARAM_NAME_SECONDNAME,
                request.getParameter(AttributesManager.PARAM_NAME_SECONDNAME));
        request.setAttribute(AttributesManager.PARAM_NAME_EMAIL,
                request.getParameter(AttributesManager.PARAM_NAME_EMAIL));
        request.setAttribute(AttributesManager.PARAM_NAME_LOGIN,
                request.getParameter(AttributesManager.PARAM_NAME_LOGIN));
        request.setAttribute(AttributesManager.PARAM_NAME_PASSWORD,
                request.getParameter(AttributesManager.PARAM_NAME_PASSWORD));
    }

}
