/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.impl.mysql;

import testingsystem.dao.intefaces.StudentDAO;
import testingsystem.dao.DAOFactory;
import testingsystem.dao.intefaces.AnswerDAO;
import testingsystem.dao.intefaces.GroupTestDAO;
import testingsystem.dao.intefaces.QuestionDAO;
import testingsystem.dao.intefaces.ResultDAO;
import testingsystem.dao.intefaces.SiteRoleDAO;
import testingsystem.dao.intefaces.SiteUserDAO;
import testingsystem.dao.intefaces.StudentGroupDAO;
import testingsystem.dao.intefaces.TestDAO;
import testingsystem.dao.intefaces.TutorDAO;

/**
 *
 * @author mirman
 */
public class MySqlDaoFactory extends DAOFactory {
    
    @Override
    public AnswerDAO createAnswerDAO() {
        return new MySQLAnswerDAO();
    }

    @Override
    public GroupTestDAO createGroupTestDAO() {
        return new MySQLGroupTestDAO();
    }

    @Override
    public QuestionDAO createQuestionDAO() {
        return new MySQLQuestionDAO();
    }

    @Override
    public ResultDAO createResultDAO() {
        return new MySQLResultDAO();
    }

    @Override
    public SiteRoleDAO createSiteRoleDAO() {
        return new MySQLSiteRoleDAO();
    }

    @Override
    public SiteUserDAO createSiteUserDAO() {
        return new MySQLSiteUserDAO();
    }

    @Override
    public StudentDAO createStudentDAO() {
        return new MySQLStudentDAO();
    }

    @Override
    public StudentGroupDAO createStudentGroupDAO() {
        return new MySQLStudentGroupDAO();
    }

    @Override
    public TestDAO createTestDAO() {
        return new MySQLTestDAO();
    }

    @Override
    public TutorDAO createTutorDAO() {
        return new MySQLTutorDAO();
    }

}
