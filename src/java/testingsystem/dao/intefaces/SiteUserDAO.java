/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.SiteUser;

/**
 *
 * @author mirman
 */
public interface SiteUserDAO {

    List<SiteUser> getAllSiteUsers();

    int insert(SiteUser siteUser);

    void update(SiteUser siteUser);

    void delete(SiteUser siteUser);

    SiteUser getByUserName(String username);

    SiteUser getByID(int id);

    SiteUser getByEmail(String email);
}
