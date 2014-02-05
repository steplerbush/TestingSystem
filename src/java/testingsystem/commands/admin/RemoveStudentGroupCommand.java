/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.commands.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import testingsystem.commands.ICommand;
import testingsystem.controllers.FrontController;
import testingsystem.manager.AttributesManager;
import testingsystem.manager.ConfigurationManager;
import testingsystem.manager.MessageManager;
import testingsystem.model.beans.Student;
import testingsystem.model.beans.StudentGroup;
import testingsystem.model.bl.AdminBL;
import testingsystem.model.bl.UserBL;

/**
 *
 * @author mirman
 */
public class RemoveStudentGroupCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        //get group from session
        StudentGroup group = (StudentGroup) session.getAttribute(
                AttributesManager.ATTRIBUTE_SELECTED_STUDENTGROUP);
        //if error occured
        if (group == null) {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.OPERATION_ERROR_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ERROR_PAGE_PATH);
        } else if (group.getId() == 1) { //if this group if default group
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.DEFAULT_GROUP_REMOVING_ERROR_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ERROR_PAGE_PATH);
        } else { //if ok - get students of the group
            List<Student> students = (List<Student>) session.getAttribute(
                    AttributesManager.ATTRIBUTE_SELECTED_GROUP_STUDENTS);
            if (students != null) { //if there are students-send to defaultgroup
                for (Student s : students) {
                    s.setGroupId(1);
                    FrontController.getDAOFactory().createStudentDAO()
                            .update(s);
                } //clear session
                session.removeAttribute(
                        AttributesManager.ATTRIBUTE_SELECTED_GROUP_STUDENTS);
            }
            //removing grouptest objects - opened tests for selected group
            AdminBL abl = new AdminBL(FrontController.getDAOFactory());
            abl.removeGroupTestsByGroupId(group.getId());
            //remove selected group
            FrontController.getDAOFactory()
                    .createStudentGroupDAO().delete(group);
            //clear session
            session.removeAttribute(
                    AttributesManager.ATTRIBUTE_SELECTED_STUDENTGROUP);
            session.setAttribute(AttributesManager.ATTRIBUTE_GROUPS_LIST,
                    new UserBL(FrontController.getDAOFactory()).getGroupList());
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        }
    }
}
