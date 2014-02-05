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
import testingsystem.dao.Constants;
import testingsystem.dao.DBConnectionPool;
import testingsystem.dao.intefaces.AnswerDAO;
import testingsystem.model.beans.Answer;
import org.apache.log4j.*;

/**
 *
 * @author mirman
 */
public class MySQLAnswerDAO implements AnswerDAO, IMySQLQueries{
    private DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement(); 
                    ResultSet rs = statement.executeQuery(SELECT_ALL_ANSWERS)) {
                while (rs.next()) {
                    Answer answer = new Answer();
                    answer.setId(rs.getInt(ID));
                    answer.setQuestionId(rs.getInt(QUESTION_ID));
                    answer.setIsCorrect(rs.getBoolean(IS_CORRECT));
                    answers.add(answer);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return answers;
    }

    @Override
    public int insert(Answer answer) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_ANSWERS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, answer.getQuestionId());
                query.setBoolean(2, answer.isCorrect());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        answer.setId(generatedKey = rs.getInt(1));
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
    public void update(Answer answer) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        UPDATE_ANSWERS);
                query.setInt(1, answer.getQuestionId());
                query.setBoolean(2, answer.isCorrect());
                query.setInt(3, answer.getId());
                query.executeUpdate();
            }
        } catch (SQLException ex) {
            Constants.logger.error(ex);
        }
    }

    @Override
    public void delete(Answer answer) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        DELETE_ANSWER);
                query.setInt(1, answer.getId());
                query.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }
}
