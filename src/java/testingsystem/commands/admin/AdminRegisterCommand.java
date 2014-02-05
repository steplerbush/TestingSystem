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
import testingsystem.controllers.FrontController;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.beans.SiteUser;
import testingsystem.model.bl.UserBL;

/**
 *
 * @author mirman
 */
public class AdminRegisterCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //getting data from page
        String login = request.getParameter(AttributesManager.PARAM_ADM_LOGIN);
        String pass = request.getParameter(AttributesManager.PARAM_ADM_PASS);
        String email = request.getParameter(AttributesManager.PARAM_ADM_EMAIL);
        String firstname = request.getParameter(
                AttributesManager.PARAM_ADM_FIRSTNAME);
        String secondname = request.getParameter(
                AttributesManager.PARAM_ADM_SECONDNAME);
        //if any field is empty
        if (login.equals("") || pass.equals("") || email.equals("")
                || firstname.equals("") || secondname.equals("")) {
            reuseInputtedFields(request, login, pass, email, firstname,
                    secondname);
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.CREATE_EMPTY_ADMIN_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        }
        UserBL ubl = new UserBL(FrontController.getDAOFactory());
        if (ubl.emailExists(email)) {
            reuseInputtedFields(request, login, pass, email, firstname,
                    secondname);
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.REGISTER_EMAIL_EXISTS_ERROR);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        } else if (ubl.userExists(login)) {
            reuseInputtedFields(request, login, pass, email, firstname,
                    secondname);
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.REGISTER_LOGIN_EXISTS_ERROR);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        } else {
            SiteUser admin = new SiteUser();
            admin.setEmail(email);
            admin.setFirstName(firstname);
            admin.setLogin(login);
            admin.setPassword(pass);
            admin.setSecondName(secondname);
            ubl.registerAdmin(admin);
            clearRegSession(request);
            request.setAttribute(AttributesManager.ATTRIBUTE_OK_MESSAGE,
                    MessageManager.CREATED_ADMIN_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        }

    }

    private void clearRegSession(HttpServletRequest request) {
        request.removeAttribute(AttributesManager.PARAM_ADM_FIRSTNAME);
        request.removeAttribute(AttributesManager.PARAM_ADM_SECONDNAME);
        request.removeAttribute(AttributesManager.PARAM_ADM_EMAIL);
        request.removeAttribute(AttributesManager.PARAM_ADM_LOGIN);
        request.removeAttribute(AttributesManager.PARAM_ADM_PASS);
        request.removeAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE);
    }

    private void reuseInputtedFields(HttpServletRequest request, String login,
            String pass, String email, String firstname, String secondname) {
        request.setAttribute(AttributesManager.PARAM_ADM_FIRSTNAME, firstname);
        request.setAttribute(AttributesManager.PARAM_ADM_SECONDNAME,
                secondname);
        request.setAttribute(AttributesManager.PARAM_ADM_EMAIL, email);
        request.setAttribute(AttributesManager.PARAM_ADM_LOGIN, login);
        request.setAttribute(AttributesManager.PARAM_ADM_PASS, pass);
    }
}
