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
import testingsystem.dao.intefaces.TutorDAO;
import testingsystem.model.beans.Tutor;

/**
 *
 * @author mirman
 */
class MySQLTutorDAO implements TutorDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<Tutor> getAllTutors() {
        List<Tutor> tutors = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs = statement.executeQuery(SELECT_ALL_TUTORS)) {
                while (rs.next()) {
                    Tutor tutor = new Tutor();
                    tutor.setId(rs.getInt(ID));
                    tutor.setUserId(rs.getInt(USER_ID));
                    tutor.setInfo(rs.getString(INFO));
                    tutor.setTelephone(TELEPHONE);
                    tutor.setApproved(rs.getBoolean(IS_APPROVED));
                    tutors.add(tutor);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return tutors;
    }

    @Override
    public List<Tutor> getUncheckedTutors() {
        List<Tutor> tutors = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs = statement.executeQuery(SELECT_UNCHECKED_TUTORS)) {
                while (rs.next()) {
                    Tutor tutor = new Tutor();
                    tutor.setId(rs.getInt(ID));
                    tutor.setUserId(rs.getInt(USER_ID));
                    tutor.setInfo(rs.getString(INFO));
                    tutor.setTelephone(TELEPHONE);
                    tutor.setApproved(rs.getBoolean(IS_APPROVED));
                    tutors.add(tutor);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return tutors;
    }

    @Override
    public int insert(Tutor tutor) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_TUTORS, Statement.RETURN_GENERATED_KEYS);
                query.setInt(1, tutor.getUserId());
                query.setString(2, tutor.getInfo());
                query.setString(3, tutor.getTelephone());
                query.setBoolean(4, tutor.isApproved());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        tutor.setId(generatedKey = rs.getInt(1));
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
    public void update(Tutor tutor) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_TUTORS);
                query.setInt(1, tutor.getUserId());
                query.setString(2, tutor.getInfo());
                query.setString(3, tutor.getTelephone());
                query.setBoolean(4, tutor.isApproved());
                query.setInt(5, tutor.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(Tutor tutor) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_TUTORS);
                query.setInt(1, tutor.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public Tutor getById(int id) {
        Tutor tutor = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_TUTOR_BY_ID);
                query.setInt(1, id);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    tutor = new Tutor();
                    tutor.setId(rs.getInt(ID));
                    tutor.setUserId(rs.getInt(USER_ID));
                    tutor.setInfo(rs.getString(INFO));
                    tutor.setTelephone(rs.getString(TELEPHONE));
                    tutor.setApproved(rs.getBoolean(IS_APPROVED));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return tutor;
    }

    @Override
    public Tutor getByUserId(int userId) {
        Tutor tutor = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_TUTOR_BY_USERID);
                query.setInt(1, userId);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    tutor = new Tutor();
                    tutor.setId(rs.getInt(ID));
                    tutor.setUserId(rs.getInt(USER_ID));
                    tutor.setInfo(rs.getString(INFO));
                    tutor.setTelephone(rs.getString(TELEPHONE));
                    tutor.setApproved(rs.getBoolean(IS_APPROVED));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return tutor;
    }

}
