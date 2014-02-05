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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import testingsystem.dao.DBConnectionPool;
import testingsystem.dao.intefaces.TestLocaleDAO;
import testingsystem.model.beans.TestLocale;

/**
 *
 * @author mirman
 */
class MySQLTestLocaleDAO implements TestLocaleDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public TestLocale getTestLocale(int testId, int localeId) {
        TestLocale testLocale = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_TESTLOCALE);
                query.setInt(1, testId);
                query.setInt(2, localeId);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    testLocale = new TestLocale();
                    testLocale.setId(rs.getInt(ID));
                    testLocale.setTestId(rs.getInt(TEST_ID));
                    testLocale.setLangId(rs.getInt(LANG_ID));
                    testLocale.setTitle(rs.getString(TITLE));
                    testLocale.setDescription(rs.getString(DESCRIPTION));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return testLocale;
    }

    @Override
    public int insert(TestLocale testLocale) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_TESTLOCALE, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, testLocale.getTestId());
                query.setInt(2, testLocale.getLangId());
                query.setString(3, testLocale.getTitle());
                query.setString(4, testLocale.getDescription());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        testLocale.setId(generatedKey = rs.getInt(1));
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
    public void update(TestLocale testLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_TESTLOCALE);
                query.setInt(1, testLocale.getTestId());
                query.setInt(2, testLocale.getLangId());
                query.setString(3, testLocale.getTitle());
                query.setString(4, testLocale.getDescription());
                query.setInt(5, testLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(TestLocale testLocale) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_TESTLOCALE);
                query.setInt(1, testLocale.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
