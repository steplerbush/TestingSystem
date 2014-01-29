/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao.intefaces;

import java.util.List;
import testingsystem.model.beans.SiteRole;

/**
 *
 * @author mirman
 */
public interface SiteRoleDAO {

    List<SiteRole> getAllSiteRoles();

    int insert(SiteRole siteRole);

    void update(SiteRole siteRole);

    void delete(SiteRole siteRole);
    
    SiteRole getById(int id);
}
