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
import testingsystem.controllers.FrontController;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.model.beans.SiteUser;
import testingsystem.model.bl.UserBL;

/**
 *
 * @author mirman
 */
public class SubmitRegisterCommand implements ICommand {

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
                    + " tried to submit register"
                    + request.getRemoteAddr());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.INDEX_PAGE_PATH);
        }
        HttpSession session = request.getSession();
        //get reg user from session
        SiteUser siteUser = (SiteUser) session.getAttribute(
                AttributesManager.ATTRIBUTE_REG_USER);
        //if reguser was got from session
        if (siteUser != null) {
            UserBL uBL = new UserBL(FrontController.getDAOFactory());
            // if reguser is a student
            if (session.getAttribute(AttributesManager.ATTRIBUTE_REG_USER_ROLE)
                    .equals(AttributesManager.STUDENT_ROLE)) {
                //getting the value of group parameter
                String groupString = request.getParameter(
                        AttributesManager.PARAM_NAME_STUDENT_GROUP);
                //registering the student-user
                uBL.registerStudent(siteUser, groupString,
                        (String) session.getAttribute(
                                AttributesManager.ATTRIBUTE_REG_USER_ROLE));
                // if reguser is a tutor
            } else if (session.getAttribute(
                    AttributesManager.ATTRIBUTE_REG_USER_ROLE).equals(
                            AttributesManager.TUTOR_ROLE)) {
                //getting values of info and telephone from session
                String info = request.getParameter(
                        AttributesManager.PARAM_NAME_TUTOR_INFO);
                String telephone = request.getParameter(
                        AttributesManager.PARAM_NAME_TELEPHONE);
                //registering the tutor-user
                uBL.registerTutor(siteUser, info, telephone,
                        (String) session.getAttribute(
                                AttributesManager.ATTRIBUTE_REG_USER_ROLE));
            }
            //removing temporary attributes---???cant invalidate the session?
            session.removeAttribute(AttributesManager.ATTRIBUTE_REG_USER);
            session.removeAttribute(AttributesManager.ATTRIBUTE_USER_ROLE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.REGISTER_DONE_PAGE_PATH);
        } else {
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.REGISTER_PAGE_PATH);
        }
    }

}
