/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testingsystem.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import testingsystem.dao.DBConnectionPool;
import testingsystem.dao.intefaces.QuestionDAO;
import testingsystem.model.beans.Question;

/**
 *
 * @author mirman
 */
class MySQLQuestionDAO implements QuestionDAO, IMySQLQueries {
    
    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs =statement.executeQuery(SELECT_ALL_QUESTIONS)){
                while (rs.next()) {
                    Question question = new Question();
                    question.setId(rs.getInt(ID));
                    question.setTestId(rs.getInt(TEST_ID));
                    question.setNumber(rs.getInt(QUESTION_NUMBER));
                    question.setWeight(rs.getInt(WEIGHT));
                    questions.add(question);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return questions;
    }

    @Override
    public int insert(Question question) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_QUESTIONS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, question.getTestId());
                query.setInt(2, question.getNumber());
                query.setInt(3, question.getWeight());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        question.setId(generatedKey = rs.getInt(1));
                    }
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return generatedKey;
    }

    @Override
    public void update(Question question) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(UPDATE_QUESTIONS);
                query.setInt(1, question.getTestId());
                query.setInt(2, question.getNumber());
                query.setInt(3, question.getWeight());
                query.setInt(4, question.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(Question question) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(DELETE_QUESTION);
                query.setInt(1, question.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }
}
