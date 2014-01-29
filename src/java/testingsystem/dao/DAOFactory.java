/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao;

import testingsystem.dao.intefaces.StudentDAO;
import testingsystem.dao.impl.mysql.MySqlDaoFactory;
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
public abstract class DAOFactory {

    public abstract AnswerDAO createAnswerDAO();

    public abstract GroupTestDAO createGroupTestDAO();

    public abstract QuestionDAO createQuestionDAO();

    public abstract ResultDAO createResultDAO();

    public abstract SiteRoleDAO createSiteRoleDAO();

    public abstract SiteUserDAO createSiteUserDAO();

    public abstract StudentDAO createStudentDAO();

    public abstract StudentGroupDAO createStudentGroupDAO();

    public abstract TestDAO createTestDAO();

    public abstract TutorDAO createTutorDAO();

    public static DAOFactory getDAOFactory(String DAOFactoryName) {
        try {
            switch (DAOFactoryName) {
                case Constants.MYSQL_DAO_FACTORY:
                    return new MySqlDaoFactory();
                default:
                    return null;
            }
        } catch (Exception ex) {
            return null;
        }

    }
}
