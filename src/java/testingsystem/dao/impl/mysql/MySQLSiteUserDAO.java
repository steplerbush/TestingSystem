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
import testingsystem.dao.intefaces.SiteUserDAO;
import testingsystem.model.beans.SiteUser;

/**
 *
 * @author mirman
 */
class MySQLSiteUserDAO implements SiteUserDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<SiteUser> getAllSiteUsers() {
        List<SiteUser> siteUsers = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs
                    = statement.executeQuery(SELECT_ALL_SITEUSERS)) {
                while (rs.next()) {
                    SiteUser siteUser = new SiteUser();
                    siteUser.setId(rs.getInt(ID));
                    siteUser.setFirstName(rs.getString(FIRST_NAME));
                    siteUser.setSecondName(rs.getString(SECOND_NAME));
                    siteUser.setEmail(rs.getString(EMAIL));
                    siteUser.setLogin(rs.getString(LOGIN));
                    siteUser.setPassword(rs.getString(PASSWORD));
                    siteUser.setRoleId(rs.getInt(ROLE_ID));
                    siteUsers.add(siteUser);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return siteUsers;
    }

    @Override
    public int insert(SiteUser siteUser) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_SITEUSERS, Statement.RETURN_GENERATED_KEYS);
                query.setString(1, siteUser.getFirstName());
                query.setString(2, siteUser.getSecondName());
                query.setString(3, siteUser.getEmail());
                query.setString(4, siteUser.getLogin());
                query.setString(5, siteUser.getPassword());
                query.setInt(6, siteUser.getRoleId());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        siteUser.setId(generatedKey = rs.getInt(1));
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
    public void update(SiteUser siteUser) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(UPDATE_SITEUSERS);
                query.setString(1, siteUser.getFirstName());
                query.setString(2, siteUser.getSecondName());
                query.setString(3, siteUser.getEmail());
                query.setString(4, siteUser.getLogin());
                query.setString(5, siteUser.getPassword());
                query.setInt(6, siteUser.getRoleId());
                query.setInt(7, siteUser.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(SiteUser siteUser) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(DELETE_SITEUSERS);
                query.setInt(1, siteUser.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public SiteUser getByUserName(String username) {
        SiteUser siteUser = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_SITEUSER_BY_USERNAME);
                query.setString(1, username);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    siteUser = new SiteUser();
                    siteUser.setId(rs.getInt(ID));
                    siteUser.setFirstName(rs.getString(FIRST_NAME));
                    siteUser.setSecondName(rs.getString(SECOND_NAME));
                    siteUser.setEmail(rs.getString(EMAIL));
                    siteUser.setLogin(rs.getString(LOGIN));
                    siteUser.setPassword(rs.getString(PASSWORD));
                    siteUser.setRoleId(rs.getInt(ROLE_ID));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return siteUser;
    }

    @Override
    public SiteUser getByEmail(String email) {
        SiteUser siteUser = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_SITEUSER_BY_EMAIL);
                query.setString(1, email);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    siteUser = new SiteUser();
                    siteUser.setId(rs.getInt(ID));
                    siteUser.setFirstName(rs.getString(FIRST_NAME));
                    siteUser.setSecondName(rs.getString(SECOND_NAME));
                    siteUser.setEmail(rs.getString(EMAIL));
                    siteUser.setLogin(rs.getString(LOGIN));
                    siteUser.setPassword(rs.getString(PASSWORD));
                    siteUser.setRoleId(rs.getInt(ROLE_ID));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return siteUser;
    }

    @Override
    public SiteUser getByID(int id) {
        SiteUser siteUser = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_SITEUSER_BY_ID);
                query.setInt(1, id);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    siteUser = new SiteUser();
                    siteUser.setId(rs.getInt(ID));
                    siteUser.setFirstName(rs.getString(FIRST_NAME));
                    siteUser.setSecondName(rs.getString(SECOND_NAME));
                    siteUser.setEmail(rs.getString(EMAIL));
                    siteUser.setLogin(rs.getString(LOGIN));
                    siteUser.setPassword(rs.getString(PASSWORD));
                    siteUser.setRoleId(rs.getInt(ROLE_ID));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return siteUser;
    }
}
