/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import testingsystem.commands.ICommand;
import testingsystem.controllers.FrontController;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.bl.AdminBL;

/**
 *
 * @author mirman
 */
public class CreateStudentGroupCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(AttributesManager.PARAM_GROUP_NAME);
        int number = Integer.parseInt(request.getParameter(AttributesManager.PARAM_GROUP_NUMBER));
        AdminBL abl = new AdminBL(FrontController.getDAOFactory());
        if (abl.insertStudentGroup(name, number)) {
            //OK message
            request.setAttribute(AttributesManager.ATTRIBUTE_OK_MESSAGE,
                        MessageManager.CREATED_STUDENTGROUP_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        } else {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                        MessageManager.CREATE_EXIST_STUDENTGROUP_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        }

    }

}
