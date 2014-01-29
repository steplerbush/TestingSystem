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
import testingsystem.dao.intefaces.GroupTestDAO;
import testingsystem.model.beans.GroupTest;

/**
 *
 * @author mirman
 */
class MySQLGroupTestDAO implements GroupTestDAO, IMySQLQueries {
    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<GroupTest> getAllGroupTests() {
        List<GroupTest> groupTests = new ArrayList<>();
        try{
            Connection myConnection = connPool.getConnection();
            try(Statement statement = myConnection.createStatement(); 
                    ResultSet rs = statement.executeQuery(
                            SELECT_ALL_GROUP_TESTS)) {
                while(rs.next()){
                    GroupTest groupTest = new GroupTest();
                    groupTest.setId(rs.getInt(ID));
                    groupTest.setGroupId(rs.getInt(GROUP_ID));
                    groupTest.setTestId(rs.getInt(TEST_ID));
                    groupTests.add(groupTest);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        }catch(SQLException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return groupTests;
    }

    @Override
    public int insert(GroupTest groupTest) {
        int generatedKey = 0;
        try{
            Connection myConnection = connPool.getConnection();
            try(Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_GROUPTESTS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, groupTest.getGroupId());
                query.setInt(2, groupTest.getTestId());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if( rs.next() )
                        groupTest.setId(generatedKey = rs.getInt(1));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        }catch(SQLException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return generatedKey;
    }

    @Override
    public void update(GroupTest groupTest) {
        try{
            Connection myConnection = connPool.getConnection();
            try(Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        UPDATE_GROUPTESTS);
                query.setInt(1, groupTest.getGroupId());
                query.setInt(2, groupTest.getTestId());
                query.setInt(3, groupTest.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        }catch(SQLException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }
    
    @Override
    public void delete(GroupTest groupTest) {
        try{
            Connection myConnection = connPool.getConnection();
            try(Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        DELETE_GROUPTEST);
                query.setInt(1, groupTest.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        }catch(SQLException ex){
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }
    
}
