/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.bl;

import java.util.ArrayList;
import java.util.List;
import testingsystem.controllers.FrontController;
import testingsystem.dao.DAOFactory;
import testingsystem.dao.intefaces.SiteUserDAO;
import testingsystem.dao.intefaces.StudentDAO;
import testingsystem.dao.intefaces.StudentGroupDAO;
import testingsystem.dao.intefaces.TutorDAO;
import testingsystem.model.beans.*;

/**
 *
 * @author mirman
 */
public class AdminBL {

    DAOFactory factory;

    public AdminBL(DAOFactory factory) {
        this.factory = factory;
    }

    public List<Tutor> getUnCkeckedTutors() {
        List<Tutor> tutors = factory.createTutorDAO().getUncheckedTutors();
        for (Tutor t : tutors) {
            t.setUser(factory.createSiteUserDAO().getByID(t.getUserId()));
        }
        return tutors;
    }

    public Tutor getTutorByID(int id) {
        Tutor t = factory.createTutorDAO().getById(id);
        t.setUser(factory.createSiteUserDAO().getByID(t.getUserId()));
        return t;
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        List<Student> students = factory.createStudentDAO()
                .getStudentsByGroupId(groupId);
        for (Student s : students) {
            s.setUser(factory.createSiteUserDAO().getByID(s.getUserId()));
        }
        return students;
    }

    public void insertTutor(Tutor tutor) {
        factory.createTutorDAO().insert(tutor);
    }

    public void updateTutor(Tutor tutor) {
        factory.createTutorDAO().update(tutor);
    }

    public boolean insertStudentGroup(String name, int number) {
        if (factory.createStudentGroupDAO().getStudentGroup(name, number) == null) {
            StudentGroup group = new StudentGroup();
            group.setGroupName(name);
            group.setGroupNumber(number);
            if (factory.createStudentGroupDAO().insert(group) != 0) {
                return true;
            }
        }
        return false;
    }

    public StudentGroup getStudentGroupByID(int id) {
        return factory.createStudentGroupDAO().getStudentGroup(id);
    }

    public void removeGroupTestsByGroupId(int id) {
        List<GroupTest> gts = factory.createGroupTestDAO().getAllGroupTests();
        for (GroupTest gt : gts) {
            if (gt.getGroupId() == id) {
                factory.createGroupTestDAO().delete(gt);
            }
        }
    }

}
