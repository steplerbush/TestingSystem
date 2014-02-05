/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.admin;

import com.sun.faces.renderkit.AttributeManager;
import java.util.List;
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
public class TutorApproveCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        AdminBL tbl = new AdminBL(FrontController.getDAOFactory());
        Tutor tutor = (Tutor)session.getAttribute(
                AttributesManager.ATTRIBUTE_UNCHECKED_TUTOR);
        tutor.setApproved(true);
        tbl.updateTutor(tutor);
        session.removeAttribute("uncheckedtutor");
        List<Tutor> tutors = tbl.getUnCkeckedTutors();
        session.setAttribute(AttributesManager.ATTRIBUTE_UNCHECKED_TUTORS, tutors);
        return ConfigurationManager.getInstance().getProperty(
                ConfigurationManager.ADMIN_HOME_PATH);
    }

}
