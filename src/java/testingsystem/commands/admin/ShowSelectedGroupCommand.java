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
public class ShowSelectedGroupCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //getting param id of studentgroup from selection
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(
                AttributesManager.PARAM_NAME_STUDENT_GROUP));
        //getting the student group instence from db by id
        AdminBL abl = new AdminBL(FrontController.getDAOFactory());
        StudentGroup studentGroup = abl.getStudentGroupByID(id);
        //if there are was found such student group
        if (studentGroup != null) {
            //logger
            session.setAttribute(
                    AttributesManager.ATTRIBUTE_SELECTED_STUDENTGROUP,
                    studentGroup);
            //getting students and their user accouns by id of student group
            List<Student> students = abl.getStudentsByGroupId(id);
            //if there are students in the group - save the list to session
            if (!students.isEmpty()) {
                session.setAttribute(
                        AttributesManager.ATTRIBUTE_SELECTED_GROUP_STUDENTS,
                        students);
            }
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
            //if there is no such student group - !db error!
        } else {
            request.setAttribute(AttributesManager.ATTRIBUTE_ERROR_MESSAGE,
                    MessageManager.DAO_EXCEPTION_ERROR_MESSAGE);
            return ConfigurationManager.getInstance().getProperty(
                    ConfigurationManager.ADMIN_HOME_PATH);
        }

    }
}
