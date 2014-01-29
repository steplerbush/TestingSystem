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
import testingsystem.dao.intefaces.TestDAO;
import testingsystem.model.beans.Test;

/**
 *
 * @author mirman
 */
class MySQLTestDAO implements TestDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs = statement.executeQuery(SELECT_ALL_TESTS)) {
                while (rs.next()) {
                    Test test = new Test();
                    test.setId(rs.getInt(ID));
                    test.setTitle(rs.getString(TITLE));
                    test.setDescription(rs.getString(DESCRIPTION));
                    test.setOpenTime(rs.getTimestamp(OPEN_TIME));
                    test.setCloseTime(rs.getTimestamp(CLOSE_TIME));
                    test.setDuration(rs.getTime(DURATION));
                    tests.add(test);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return tests;
    }

    @Override
    public int insert(Test test) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_TESTS, Statement.RETURN_GENERATED_KEYS);
                query.setString(1, test.getTitle());
                query.setString(2, test.getDescription());
                query.setTimestamp(3, test.getOpenTime());
                query.setTimestamp(4, test.getCloseTime());
                query.setTime(5, test.getDuration());
                query.setInt(6, test.getTutorId());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        test.setId(generatedKey = rs.getInt(1));
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
    public void update(Test test) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(UPDATE_TESTS);
                query.setString(1, test.getTitle());
                query.setString(2, test.getDescription());
                query.setTimestamp(3, test.getOpenTime());
                query.setTimestamp(4, test.getCloseTime());
                query.setTime(5, test.getDuration());
                query.setInt(6, test.getTutorId());
                query.setInt(7, test.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(Test test) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(DELETE_TESTS);
                query.setInt(1, test.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
