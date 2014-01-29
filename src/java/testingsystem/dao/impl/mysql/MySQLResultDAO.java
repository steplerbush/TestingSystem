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
import testingsystem.dao.intefaces.ResultDAO;
import testingsystem.model.beans.Result;

/**
 *
 * @author mirman
 */
class MySQLResultDAO implements ResultDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs = statement.executeQuery(SELECT_ALL_RESULTS)) {
                while (rs.next()) {
                    Result result = new Result();
                    result.setId(rs.getInt(ID));
                    result.setStudentId(rs.getInt(STUDENT_ID));
                    result.setTestId(rs.getInt(TEST_ID));
                    result.setResultTimestamp(rs.getTimestamp(RESULT_DATETIME));
                    result.setGrade(rs.getInt(GRADE));
                    results.add(result);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return results;
    }

    @Override
    public int insert(Result result) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_RESULTS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, result.getStudentId());
                query.setInt(2, result.getTestId());
                query.setTimestamp(3, result.getResultTimestamp());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        result.setId(generatedKey = rs.getInt(1));
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
    public void update(Result result) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(UPDATE_RESULTS);
                query.setInt(1, result.getStudentId());
                query.setInt(2, result.getTestId());
                query.setTimestamp(3, result.getResultTimestamp());
                query.setInt(4, result.getGrade());
                query.setInt(5, result.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(Result result) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = 
                        myConnection.prepareStatement(DELETE_RESULT);
                query.setInt(1, result.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
