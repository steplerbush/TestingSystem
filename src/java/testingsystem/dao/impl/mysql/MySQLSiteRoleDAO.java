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
import testingsystem.dao.intefaces.SiteRoleDAO;
import testingsystem.model.beans.SiteRole;

/**
 *
 * @author mirman
 */
class MySQLSiteRoleDAO implements SiteRoleDAO, IMySQLQueries {

    private final DBConnectionPool connPool = DBConnectionPool.getInstance();

    @Override
    public List<SiteRole> getAllSiteRoles() {
        List<SiteRole> siteroles = new ArrayList<>();
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement();
                    ResultSet rs 
                            = statement.executeQuery(SELECT_ALL_SITEROLES)) {
                while (rs.next()) {
                    SiteRole siterole = new SiteRole();
                    siterole.setId(rs.getInt(ID));
                    siterole.setRoleName(rs.getString(ROLE_NAME));
                    siteroles.add(siterole);
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return siteroles;
    }

    @Override
    public int insert(SiteRole siterole) {
        int generatedKey = 0;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query = myConnection.prepareStatement(
                        INSERT_INTO_SITEROLES, Statement.RETURN_GENERATED_KEYS);
                query.setString(1, siterole.getRoleName());
                query.executeUpdate();
                try (ResultSet rs = query.getGeneratedKeys()) {
                    if (rs.next()) {
                        siterole.setId(generatedKey = rs.getInt(1));
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
    public void update(SiteRole siterole) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(UPDATE_SITEROLES);
                query.setString(1, siterole.getRoleName());
                query.setInt(2, siterole.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public void delete(SiteRole siteRole) {
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query 
                        = myConnection.prepareStatement(DELETE_SITEROLE);
                query.setInt(1, siteRole.getId());
                query.executeUpdate();
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
    }

    @Override
    public SiteRole getById(int id) {
        SiteRole siteRole = null;
        try {
            Connection myConnection = connPool.getConnection();
            try (Statement statement = myConnection.createStatement()) {
                PreparedStatement query
                        = myConnection.prepareStatement(GET_SITEROLE_BY_ID);
                query.setInt(1, id);
                ResultSet rs = query.executeQuery();
                if (rs.next()) {
                    siteRole = new SiteRole();
                    siteRole.setId(rs.getInt(ID));
                    siteRole.setRoleName(rs.getString(ROLE_NAME));
                }
            } finally {
                connPool.returnConnection(myConnection);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }

        return siteRole;
    }
}
