/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.model.bl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import testingsystem.dao.DAOFactory;
import testingsystem.dao.intefaces.SiteRoleDAO;
import testingsystem.dao.intefaces.SiteUserDAO;
import testingsystem.dao.intefaces.StudentDAO;
import testingsystem.dao.intefaces.StudentGroupDAO;
import testingsystem.dao.intefaces.TutorDAO;
import testingsystem.manager.AttributesManager;
import testingsystem.model.beans.SiteRole;
import testingsystem.model.beans.SiteUser;
import testingsystem.model.beans.Student;
import testingsystem.model.beans.StudentGroup;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
public class UserBL {

    private final SiteUserDAO siteUserDAO;
    private final SiteRoleDAO siteRoleDAO;
    private final StudentGroupDAO studentGroupDAO;
    private final StudentDAO studentDAO;
    private final TutorDAO tutorDAO;

    public UserBL(DAOFactory factory) {
        this.siteUserDAO = factory.createSiteUserDAO();
        this.siteRoleDAO = factory.createSiteRoleDAO();
        this.studentGroupDAO = factory.createStudentGroupDAO();
        this.tutorDAO = factory.createTutorDAO();
        this.studentDAO = factory.createStudentDAO();
    }

    public SiteUser getSiteUser(String username, String password) {
        SiteUser siteUser = siteUserDAO.getByUserName(username);
        if (siteUser != null && siteUser.getPassword().equals(password)) {
            return siteUser;
        }
        return null;
    }

    public boolean userExists(String username) {
        SiteUser siteUser = siteUserDAO.getByUserName(username);
        return siteUser != null;
    }

    public SiteRole getSiteRole(int roleId) {
        return siteRoleDAO.getById(roleId);
    }

    public SiteRole getSiteRole(SiteUser siteUser) {
        return siteRoleDAO.getById(siteUser.getRoleId());
    }

    public String testRegisterUser(String email, String username) {
        if (userExists(username)) {
            return AttributesManager.DAO_MESSAGE_LOGIN_EXISTS;
        } else if (emailExists(email)) {
            return AttributesManager.DAO_MESSAGE_EMAIL_EXISTS;
        } else {
            return AttributesManager.EMPTY_STRING;
        }
    }

    public boolean emailExists(String email) {
        SiteUser siteUser = siteUserDAO.getByEmail(email);
        return siteUser != null;
    }

    public SiteUser createTempUser(String firstName, String secondName,
            String email, String login, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setFirstName(firstName);
        siteUser.setSecondName(secondName);
        siteUser.setEmail(email);
        siteUser.setLogin(login);
        siteUser.setPassword(password);
        return siteUser;
    }

    public List<StudentGroup> getGroupList() {
        return studentGroupDAO.getAllStudentGroups();
    }
    
    public int getStudentGroupID(String groupString) {
        List<StudentGroup> groupList = getGroupList();
        for (StudentGroup group : groupList) {
            if (group.toString().equals(groupString)) {
                return group.getId();
            }
        }
        return -1;
    }

    public void registerAdmin(SiteUser siteUser){
        for (SiteRole sr : siteRoleDAO.getAllSiteRoles()) {
            if (sr.getRoleName().equals(AttributesManager.ADMIN_ROLE)) {
                siteUser.setRoleId(sr.getId());
                break;
            }
        }
        siteUserDAO.insert(siteUser);
    }
    
    public void registerStudent(SiteUser siteUser,
            String groupString) {
        int groupId = getStudentGroupID(groupString);
        List<SiteRole> ss = siteRoleDAO.getAllSiteRoles();
        for (SiteRole sr : ss) {
            if (sr.getRoleName().equals(AttributesManager.STUDENT_ROLE)) {
                siteUser.setRoleId(sr.getId());
                break;
            }
        }
        Student s = new Student();
        s.setGroupId(groupId);
        siteUser.setId(siteUserDAO.insert(siteUser));
        s.setUserId(siteUser.getId());
        studentDAO.insert(s);
    }

    public void registerTutor(SiteUser siteUser, String info, String telephone) {
        Tutor t = new Tutor();
        t.setInfo(info);
        t.setTelephone(telephone);
        for (SiteRole sr : siteRoleDAO.getAllSiteRoles()) {
            if (sr.getRoleName().equals(AttributesManager.TUTOR_ROLE)) {
                siteUser.setRoleId(sr.getId());
                break;
            }
        }
        siteUser.setId(siteUserDAO.insert(siteUser));
        t.setUserId(siteUser.getId());
        tutorDAO.insert(t);
    }
}
