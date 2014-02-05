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
import testingsystem.dao.intefaces.AnswerLocaleDAO;
import testingsystem.model.beans.AnswerLocale;

/**
 *
 * @author mirman
 */
class MySQLAnswerLocaleDAO implements AnswerLocaleDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public AnswerLocale getAnswerLocale(int answerId, int localeId) {
        AnswerLocale answerLocale = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_ANSWERLOCALE);
                query.setInt(1, answerId);
                query.setInt(2, localeId);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    answerLocale = new AnswerLocale();
                    answerLocale.setId(rs.getInt(ID));
                    answerLocale.setAnswerId(rs.getInt(ANSWER_ID));
                    answerLocale.setLangId(rs.getInt(LANG_ID));
                    answerLocale.setAnswerText(rs.getString(ANSWER_TEXT));
                    answerLocale.setImageSrc(rs.getString(IMAGE_SRC));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return answerLocale;
    }

    @Override
    public int insert(AnswerLocale answerLocale) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_ANSWERLOCALE,
                        Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, answerLocale.getAnswerId());
                query.setInt(2, answerLocale.getLangId());
                query.setString(3, answerLocale.getAnswerText());
                query.setString(4, answerLocale.getImageSrc());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        answerLocale.setId(generatedKey = rs.getInt(1));
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
    public void update(AnswerLocale answerLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_ANSWERLOCALE);
                query.setInt(1, answerLocale.getAnswerId());
                query.setInt(2, answerLocale.getLangId());
                query.setString(3, answerLocale.getAnswerText());
                query.setString(4, answerLocale.getImageSrc());
                query.setInt(5, answerLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(AnswerLocale answerLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_ANSWERLOCALE);
                query.setInt(1, answerLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
