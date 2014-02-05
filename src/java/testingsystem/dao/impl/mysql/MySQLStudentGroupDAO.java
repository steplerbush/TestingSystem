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
import testingsystem.dao.intefaces.StudentGroupDAO;
import testingsystem.model.beans.Student;
import testingsystem.model.beans.StudentGroup;

/**
 *
 * @author mirman
 */
public class MySQLStudentGroupDAO implements StudentGroupDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<StudentGroup> getAllStudentGroups() {
        List<StudentGroup> groups = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs = statement.executeQuery(
                            SELECT_ALL_STUDENTGROUPS)) {
                while (rs.next()) {
                    StudentGroup group = new StudentGroup();
                    group.setId(rs.getInt(ID));
                    group.setGroupName(rs.getString(GROUP_NAME));
                    group.setGroupNumber(rs.getInt(GROUP_NUMBER));
                    groups.add(group);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return groups;
    }

    @Override
    public int insert(StudentGroup studentGroup) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_STUDENTGROUPS,
                        Statement.RETURN_GENERATED_KEYS);
                query.setString(1, studentGroup.getGroupName());
                query.setInt(2, studentGroup.getGroupNumber());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        studentGroup.setId(generatedKey = rs.getInt(1));
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
    public void update(StudentGroup studentGroup) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_STUDENTGROUPS);
                query.setString(1, studentGroup.getGroupName());
                query.setInt(2, studentGroup.getGroupNumber());
                query.setInt(3, studentGroup.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(StudentGroup studentGroup) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_STUDENTGROUP);
                query.setInt(1, studentGroup.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public StudentGroup getStudentGroup(String name, int number) {
        StudentGroup group = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_STUDENTGROUP_BY_NAME_NUMBER);
                query.setString(1, name);
                query.setInt(2, number);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    group = new StudentGroup();
                    group.setId(rs.getInt(GROUP_ID));
                    group.setGroupName(rs.getString(GROUP_NAME));
                    group.setGroupNumber(rs.getInt(GROUP_NUMBER));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return group;
    }

    @Override
    public StudentGroup getStudentGroup(int id) {
        StudentGroup studentGroup = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_STUDENTGROUP_BY_ID);
                query.setInt(1, id);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    studentGroup = new StudentGroup();
                    studentGroup.setId(rs.getInt(ID));
                    studentGroup.setGroupName(rs.getString(GROUP_NAME));
                    studentGroup.setGroupNumber(rs.getInt(GROUP_NUMBER));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }

        return studentGroup;
    }

}
