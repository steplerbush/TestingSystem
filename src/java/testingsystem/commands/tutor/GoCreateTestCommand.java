/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.tutor;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import testingsystem.commands.ICommand;
import testingsystem.controllers.FrontController;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
public class GoCreateTestCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession hs = request.getSession();
        if (RequestHelper.isTutorUser(request)) {
            if (((Tutor) hs.getAttribute(
                    AttributesManager.ATTRIBUTE_USER)).isApproved()) {
                return prepareTestPage(hs);
            } else {
                return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.TUTOR_HOME_PATH);
            }
        } else {
            hs.invalidate();
            return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }

    private String prepareTestPage(HttpSession session) {
        session.setAttribute(AttributesManager.ATTRIBUTE_DURATION, AttributesManager.DURATION_NULL);
        session.setAttribute(AttributesManager.ATTRIBUTE_OPENING_TIME, new Date());
        session.setAttribute(AttributesManager.ATTRIBUTE_CLOSING_TIME, new Date());
        session.setAttribute(AttributesManager.ATTRIBUTE_LANG_LIST, 
                FrontController.getDAOFactory().createLocaleDAO()
                        .getAllLocales());
        return ConfigurationManager.getInstance().
                getProperty(ConfigurationManager.TUTOR_CREATE_TEST_PATH);
    }

}
