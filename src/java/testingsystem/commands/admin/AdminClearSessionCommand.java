/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import testingsystem.commands.ICommand;
import testingsystem.controllers.RequestHelper;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.model.beans.SiteUser;

/**
 *
 * @author mirman
 */
public class AdminClearSessionCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (RequestHelper.isAdminUser(request)) {
            session.removeAttribute(AttributesManager.ATTRIBUTE_SELECTED_GROUP_STUDENTS);
            session.removeAttribute(AttributesManager.ATTRIBUTE_SELECTED_STUDENTGROUP);
            session.removeAttribute(AttributesManager.ATTRIBUTE_UNCHECKED_TUTOR);
            session.removeAttribute(AttributesManager.ATTRIBUTE_UNCHECKED_TUTORS);
            return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.ADMIN_HOME_PATH);
        } else {
            session.invalidate();
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }
}
