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
import testingsystem.dao.intefaces.StudentDAO;
import testingsystem.model.beans.Student;

/**
 *
 * @author mirman
 */
public class MySQLStudentDAO implements StudentDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement(); 
                    ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS)){
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt(ID));
                    student.setGroupId(rs.getInt(GROUP_ID));
                    student.setUserId(rs.getInt(USER_ID));
                    students.add(student);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        } 
        return students;
    }

    @Override
    public int insert(Student s) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_STUDENTS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, s.getGroupId());
                query.setInt(2, s.getUserId());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        s.setId(generatedKey = rs.getInt(1));
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
    public void update(Student student) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(UPDATE_STUDENTS);
                query.setInt(1, student.getGroupId());
                query.setInt(2, student.getUserId());
                query.setInt(3, student.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(Student student) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(DELETE_STUDENTS);
                query.setInt(1, student.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

}
