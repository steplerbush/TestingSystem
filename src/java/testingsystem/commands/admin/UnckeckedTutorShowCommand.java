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
import testingsystem.model.beans.Tutor;
import testingsystem.model.bl.AdminBL;

/**
 *
 * @author mirman
 */
public class UnckeckedTutorShowCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(
                AttributesManager.ATTRIBUTE_SELECT_TUTOR));
        AdminBL tbl = new AdminBL(FrontController.getDAOFactory());
        Tutor t = tbl.getTutorByID(id);
        session.setAttribute(AttributesManager.ATTRIBUTE_UNCHECKED_TUTOR, t);
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.ADMIN_HOME_PATH);
    }

}
