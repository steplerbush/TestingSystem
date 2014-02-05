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
import org.apache.log4j.*;
import testingsystem.dao.DBConnectionPool;
import testingsystem.dao.intefaces.QuestionLocaleDAO;
import testingsystem.model.beans.QuestionLocale;

/**
 *
 * @author mirman
 */
class MySQLQuestionLocaleDAO implements QuestionLocaleDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public QuestionLocale getQuestionLocale(int questionId, int localeId) {
        QuestionLocale questionLocale = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_QUESTIONLOCALE);
                query.setInt(1, questionId);
                query.setInt(2, localeId);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    questionLocale = new QuestionLocale();
                    questionLocale.setId(rs.getInt(ID));
                    questionLocale.setQuestionId(rs.getInt(QUESTION_ID));
                    questionLocale.setLangId(rs.getInt(LANG_ID));
                    questionLocale.setDescription(rs.getString(DESCRIPTION));
                    questionLocale.setImageSrc(rs.getString(IMAGE_SRC));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return questionLocale;
    }

    @Override
    public int insert(QuestionLocale questionLocale) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_QUESTIONLOCALE, 
                        Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, questionLocale.getQuestionId());
                query.setInt(2, questionLocale.getLangId());
                query.setString(3, questionLocale.getDescription());
                query.setString(4, questionLocale.getImageSrc());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        questionLocale.setId(generatedKey = rs.getInt(1));
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
    public void update(QuestionLocale questionLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_QUESTIONLOCALE);
                query.setInt(1, questionLocale.getQuestionId());
                query.setInt(2, questionLocale.getLangId());
                query.setString(3, questionLocale.getDescription());
                query.setString(4, questionLocale.getImageSrc());
                query.setInt(5, questionLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(QuestionLocale questionLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_QUESTIONLOCALE);
                query.setInt(1, questionLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
